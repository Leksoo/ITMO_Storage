package ru.ifmo.rain.feponov.mapper;

import info.kgeorgiy.java.advanced.concurrent.ListIP;
import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("Duplicates")
public class IterativeParallelism implements ListIP {
    private ParallelMapper mapper;

    public IterativeParallelism() {
        mapper = null;
    }

    public IterativeParallelism(ParallelMapper mapper) {
        this.mapper = mapper;
    }

    private <L, R> R calculate(int threads, List<? extends L> list,
                               Function<List<? extends L>, ? extends R> calcFunc,
                               Function<List<? extends R>, ? extends R> joinFunc) throws InterruptedException {
        threads = Math.min(threads, list.size());
        var threadList = new ArrayList<Thread>();
        var groupSize = list.size() / threads;
        var left = list.size() % threads;
        List<List<? extends L>> groups = new ArrayList<>();
        for (int threadInd = 0, blockStart = 0; threadInd < threads; threadInd++) {
            var blockEnd = blockStart + groupSize;
            if (left > 0) {
                blockEnd++;
                left--;
            }
            groups.add(list.subList(blockStart, blockEnd));
            blockStart = blockEnd;
        }
        if (mapper != null) {
            return joinFunc.apply(mapper.map(calcFunc, groups));
        }
        var results = new ArrayList<R>(Collections.nCopies(threads, null));
        for (int i = 0; i < threads; i++) {
            final int threadInd = i;
            var thread = new Thread(() ->
                    results.set(threadInd, calcFunc.apply(groups.get(threadInd)))
            );
            threadList.add(thread);
            thread.start();
        }
        for (int i = 0; i < threads; i++) {
            threadList.get(i).join();
        }
        return joinFunc.apply(results);


    }

    @Override
    public <T> T maximum(int threads, List<? extends T> list, Comparator<? super T> comparator) throws InterruptedException {
        Function<List<? extends T>, ? extends T> max = l -> l.stream().max(comparator).orElse(null);
        return calculate(threads, list, max, max);
    }

    @Override
    public <T> T minimum(int threads, List<? extends T> list, Comparator<? super T> comparator) throws InterruptedException {
        Function<List<? extends T>, ? extends T> min = l -> l.stream().min(comparator).orElse(null);
        return calculate(threads, list, min, min);
    }

    @Override
    public <T> boolean all(int threads, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        return calculate(threads, list, l -> l.stream().allMatch(predicate), booleans -> !booleans.contains(false));
    }

    @Override
    public <T> boolean any(int threads, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        return calculate(threads, list, lists -> lists.stream().anyMatch(predicate), booleans -> booleans.contains(true));

    }

    @Override
    public String join(int threads, List<?> list) throws InterruptedException {
        return calculate(threads, list, lists -> lists.stream().map(Object::toString).collect(Collectors.joining()),
                lists -> String.join("", lists));

    }

    private <T, U> List<U> calcStreamFunction(int threads, List<? extends T> list, Function<Stream<? extends T>, Stream<? extends U>> func) throws InterruptedException {
        return calculate(threads, list, lists -> func.apply(lists.stream()).collect(Collectors.toList()),
                lists -> lists.stream().flatMap(List::stream).collect(Collectors.toList()));
    }

    @Override
    public <T> List<T> filter(int threads, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        return calcStreamFunction(threads, list, stream -> stream.filter(predicate));
    }

    @Override
    public <T, U> List<U> map(int threads, List<? extends T> list, Function<? super T, ? extends U> function) throws InterruptedException {
        return calcStreamFunction(threads, list, stream -> stream.map(function));

    }
}
