package ru.ifmo.rain.feponov.crawler;

import info.kgeorgiy.java.advanced.crawler.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class WebCrawler implements Crawler {

    private final Downloader downloader;
    private final int downloaders;
    private final int extractors;
    private final int maxPerHost;
    private final ExecutorService downloadersThreadPool;
    private final ExecutorService extractorsThreadPool;
    private final Map<String, HostThreadManager> hosts = new ConcurrentHashMap<>();

    public WebCrawler(Downloader downloader, int downloaders, int extractors, int perHost) {
        this.downloader = downloader;
        this.downloaders = downloaders;
        this.extractors = extractors;
        this.maxPerHost = perHost;
        downloadersThreadPool = Executors.newFixedThreadPool(downloaders);
        extractorsThreadPool = Executors.newFixedThreadPool(extractors);
    }

    private String getHost(String url, Map<String, IOException> exceptions) {
        try {
            return URLUtils.getHost(url);
        } catch (MalformedURLException e) {
            exceptions.put(url, e);
            return null;
        }
    }

    @Override
    public Result download(String s, int depth) {
        Set<String> probablyDownloaded = new ConcurrentSkipListSet<>();
        Map<String, IOException> exceptions = new ConcurrentHashMap<>();
        var phaser = new Phaser(1);
        download(phaser, s, depth, probablyDownloaded, exceptions);
        phaser.arriveAndAwaitAdvance();
        return new Result(new ArrayList<>(
                probablyDownloaded.stream().filter(it -> !exceptions.containsKey(it)).collect(Collectors.toList())),
                exceptions);

    }

    private void download(Phaser phaser, String url, int depth, Set<String> probablyDownloaded, Map<String, IOException> exceptions) {
        if (probablyDownloaded.contains(url) || depth == 0) {
            return;
        }
        // get HostManager
        String host = getHost(url, exceptions);
        if (host == null) {
            return;
        }
        HostThreadManager tmpManager = hosts.getOrDefault(host, null);
        final HostThreadManager manager;
        if (tmpManager == null) {
            manager = new HostThreadManager();
            hosts.putIfAbsent(host, manager);
        } else {
            manager = tmpManager;
        }
        probablyDownloaded.add(url);
        Runnable downloadTask = () -> {
            try {
                Document document = downloader.download(url);
                Runnable getLinksTask = () -> {
                    try {
                        for (var link : document.extractLinks()) {
                            download(phaser, link, depth - 1, probablyDownloaded, exceptions);
                        }
                    } catch (IOException e) {
                        exceptions.put(url, e);

                    }
                    // extraction cnt--
                    phaser.arrive();

                };
                // extraction cnt++
                phaser.register();
                extractorsThreadPool.submit(getLinksTask);

            } catch (IOException e) {
                exceptions.put(url, e);
            }

            manager.runNext();
            // download cnt--
            phaser.arrive();

        };
        // download cnt++
        phaser.register();
        manager.addTask(downloadTask);


    }


    @Override
    public void close() {
        downloadersThreadPool.shutdownNow();
        extractorsThreadPool.shutdownNow();
    }

    public static void main(String[] args) {
        if (args == null || args.length > 6 || args.length <= 1) {
            System.err.println("Unexpected number of arguments, should be from 2 to 6");
            return;
        }
        String url;
        int downloads = 20;
        int extractors = 20;
        int perHost = 20;
        int depth = 20;
        try {
            url = args[1];
            if (args.length > 5) {
                perHost = Integer.parseInt(args[5]);
            }
            if (args.length > 4) {
                extractors = Integer.parseInt(args[4]);
            }
            if (args.length > 3) {
                downloads = Integer.parseInt(args[3]);
            }
            if (args.length > 2) {
                depth = Integer.parseInt(args[2]);
            }
        } catch (NumberFormatException e) {
            System.err.println("Argument expected as a number: " + e.getMessage());
            return;
        } catch (NullPointerException e) {
            System.err.println("Expected non-null arguments: " + e.getMessage());
            return;
        }
        try (WebCrawler crawler = new WebCrawler(new CachingDownloader(), downloads, extractors, perHost)) {
            crawler.download(url, depth);
        } catch (IOException e) {
            System.err.println("CachingDownloader error: " + e.getMessage());
        }
    }

    private class HostThreadManager {
        private final LinkedList<Runnable> taskQueue = new LinkedList<>();
        private int perHostNow = 0;

        private synchronized void addTask(Runnable task) {
            // host is full -> add to waiting queue
            // otherwise -> add to pool, increase host loading
            if (maxPerHost <= perHostNow) {
                taskQueue.addFirst(task);
            } else {
                downloadersThreadPool.submit(task);
                perHostNow++;
            }
        }

        private synchronized void runNext() {
            if (taskQueue.isEmpty()) {
                perHostNow--;
            } else {
                var next = taskQueue.pollLast();
                if (next != null) {
                    downloadersThreadPool.submit(next);
                }
            }
        }
    }
}
