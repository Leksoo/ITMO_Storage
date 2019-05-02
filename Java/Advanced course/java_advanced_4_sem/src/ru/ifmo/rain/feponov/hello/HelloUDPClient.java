package ru.ifmo.rain.feponov.hello;

import info.kgeorgiy.java.advanced.hello.HelloClient;
import info.kgeorgiy.java.advanced.hello.Util;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class HelloUDPClient implements HelloClient {

    private static final int TIMEOUT = 1000;

    public static void main(String[] args) {
        if (args == null || args.length != 5) {
            System.err.println("Unexpected number of arguments, should be 5");
            return;
        }
        String host;
        String prefix;
        int port;
        int threads;
        int requests;
        try {
            host = args[0];
            port = Integer.parseInt(args[1]);
            prefix = args[2];
            threads = Integer.parseInt(args[3]);
            requests = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            System.err.println("Argument expected as a number: " + e.getMessage());
            return;
        } catch (NullPointerException e) {
            System.err.println("Expected non-null arguments: " + e.getMessage());
            return;
        }
        new HelloUDPClient().run(host, port, prefix, threads, requests);
    }

    @Override
    public void run(String host, int port, String prefix, int threads, int requests) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
        ExecutorService sendThreadPool = Executors.newFixedThreadPool(threads);
        for (int curThread = 0; curThread < threads; curThread++) {
            int finalCurThread = curThread;
            sendThreadPool.submit(() -> {
                try (DatagramSocket socket = new DatagramSocket()) {
                    socket.setSoTimeout(TIMEOUT);
                    var receivePacket = createReceivePacket(socket.getReceiveBufferSize());
                    for (int curRequest = 0; curRequest < requests; curRequest++) {
                        var requestText = prefix + finalCurThread + "_" + curRequest;
                        var sendPacket = createSendPacket(requestText, inetSocketAddress);
                        while (true) {
                            try {
                                socket.send(sendPacket);
                            } catch (IOException e) {
                                System.err.println("Error while requesting to server: " + e.getMessage());
                            }
                            try {
                                socket.receive(receivePacket);
                            } catch (IOException e) {
                                System.err.println("Error while receiving from server: " + e.getMessage());

                            }
                            String stringResponse = new String(
                                    receivePacket.getData(),
                                    receivePacket.getOffset(),
                                    receivePacket.getLength(),
                                    StandardCharsets.UTF_8
                            );
                            if (stringResponse.contains(requestText)) {
                                System.out.println(stringResponse);
                                break;
                            }
                        }
                    }
                } catch (SocketException e) {
                    System.err.println("Cannot open or bind socket: " + e.getMessage());
                }
            });

        }
        sendThreadPool.shutdown();
        try {
            sendThreadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ignored) {
        }
    }

    private DatagramPacket createSendPacket(String request, InetSocketAddress address) {
        var byteRequest = request.getBytes(StandardCharsets.UTF_8);
        return new DatagramPacket(byteRequest, request.length(), address);
    }

    private DatagramPacket createReceivePacket(int size) {
        var byteResponse = new byte[size];
        return new DatagramPacket(byteResponse, size);
    }


}
