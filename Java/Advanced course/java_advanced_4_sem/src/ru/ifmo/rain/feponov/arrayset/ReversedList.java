package ru.ifmo.rain.feponov.arrayset;

import java.util.*;

public class ReversedList<T> extends AbstractList<T> implements RandomAccess {
    private final List<T> data;

    public ReversedList(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }


    @Override
    public T get(int index) {
        return data.get(size() - index - 1);
    }

    @Override
    public int size() {
        return data.size();
    }
}
