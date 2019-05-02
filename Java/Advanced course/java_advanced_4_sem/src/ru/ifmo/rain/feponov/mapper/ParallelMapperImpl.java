package ru.ifmo.rain.feponov.mapper;

import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.*;
import java.util.function.Function;

/**
 * Implementation of {@link ParallelMapper}
 */
public class ParallelMapperImpl implements ParallelMapper {

    private final List<Thread> threadList;
    private final Queue<Task> taskQueue;

    private class TaskManager<R> {
        final int need;
        int done = 0;
        final List<R> resultList;

        TaskManager(int need) {
            this.need = need;
            resultList = new ArrayList<>(Collections.nCopies(need, null));
        }

        void set(int ind, R data) {
            resultList.set(ind, data);
        }

        boolean isWorkDone() {
            if (need == done) {
                notify();
                return true;
            }
            return false;
        }
    }

    private class Task {
        final Runnable runnable;
        final TaskManager taskManager;

        Task(TaskManager taskManager, Runnable runnable) {
            this.runnable = runnable;
            this.taskManager = taskManager;
        }
    }

    /**
     * Constructor of {@link ParallelMapperImpl} that creates <tt>threadNum</tt> threads for computing
     * functions passed by {@link #map(Function, List)}
     * @param threadNum number of threads will be created
     */
    public ParallelMapperImpl(int threadNum) {
        threadList = new ArrayList<>();
        taskQueue = new LinkedList<>();
        for (int i = 0; i < threadNum; i++) {
            var thread = new Thread(() -> {
                try {
                    while (!Thread.interrupted()) {
                        execute();
                    }
                } catch (InterruptedException ignored) {
                } finally {
                    Thread.currentThread().interrupt();
                }
            });
            threadList.add(thread);
            thread.start();
        }

    }

    private void execute() throws InterruptedException {
        Task task;
        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                taskQueue.wait();
            }
            task = taskQueue.poll();
        }
        task.runnable.run();
        synchronized (task.taskManager) {
            task.taskManager.done++;
            task.taskManager.isWorkDone();
        }
    }

    @Override
    public <T, R> List<R> map(Function<? super T, ? extends R> function, List<? extends T> args) throws InterruptedException {
        TaskManager<R> taskManager = new TaskManager<>(args.size());
        for (int i = 0; i < args.size(); i++) {
            final int ind = i;
            synchronized (taskQueue) {
                taskQueue.add(new Task(taskManager, () -> taskManager.set(ind, function.apply(args.get(ind)))));
                taskQueue.notify();
            }
        }
        synchronized (taskManager) {
            while (!taskManager.isWorkDone()) {
                taskManager.wait();
            }
        }
        return taskManager.resultList;
    }

    @Override
    public void close() {
        threadList.forEach(Thread::interrupt);
        for (var thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        }
    }
}
