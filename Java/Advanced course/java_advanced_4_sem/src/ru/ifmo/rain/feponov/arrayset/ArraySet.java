package ru.ifmo.rain.feponov.arrayset;

import java.util.*;

public class ArraySet<T> extends AbstractSet<T> implements NavigableSet<T> {
    private final List<T> data;
    private final Comparator<? super T> comparator;

    public ArraySet() {
        this(Collections.emptyList(), null);
    }

    public ArraySet(Collection<? extends T> data) {
        this(data, null);
    }

    public ArraySet(Collection<? extends T> data, Comparator<? super T> comparator) {
        Set<T> set = new TreeSet<>(comparator);
        set.addAll(data);
        this.data = new ArrayList<>(set);
        this.comparator = comparator;
    }

    private ArraySet(List<T> list, Comparator<? super T> comparator) {
        this.comparator = comparator;
        data = list;
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Iterator<T> iterator() {
        return Collections.unmodifiableList(data).iterator();
    }

    @Override
    public NavigableSet<T> descendingSet() {
        if (data instanceof ReversedList){
            return new ArraySet<>(new ArrayList<>(((ReversedList<T>) data).getData()), Collections.reverseOrder(comparator));
        }
        return new ArraySet<>(new ReversedList<>(data), Collections.reverseOrder(comparator));
    }

    @Override
    public Iterator<T> descendingIterator() {
        return descendingSet().iterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        return Collections.binarySearch(data, (T) o, comparator) >= 0;
    }

    @Override
    public T lower(T t) {
        int index = getIndexOfElement(t, false, false);
        return index >= 0 ? data.get(index) : null;
    }

    @Override
    public T floor(T t) {
        int index = getIndexOfElement(t, true, false);
        return index >= 0 ? data.get(index) : null;
    }

    @Override
    public T higher(T t) {
        int index = getIndexOfElement(t, false, true);
        return index < size() ? data.get(index) : null;
    }

    @Override
    public T ceiling(T t) {
        int index = getIndexOfElement(t, true, true);
        return index < size() ? data.get(index) : null;
    }

    @Override
    public T pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T first() {
        checkEmptiness();
        return data.get(0);
    }

    @Override
    public T last() {
        checkEmptiness();
        return data.get(size() - 1);
    }

    private int compare(T a, T b){
        if(comparator == null){
            return ((Comparable) a).compareTo(b);
        }else {
            return comparator.compare(a,b);
        }
    }

    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        if(compare(fromElement,toElement)>0){
            throw new IllegalArgumentException();
        }
        int indexFrom = getIndexOfElement(fromElement, fromInclusive, true);
        int indexTo = getIndexOfElement(toElement, toInclusive, false);
        if (indexFrom > indexTo) {
            throw new IllegalArgumentException();
        }
        // + 1 because subList method does not include toIndex
        return new ArraySet<>(data.subList(indexFrom, indexTo + 1), comparator);
    }

    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive) {
        // + 1 because subList method does not include toIndex
        return new ArraySet<>(data.subList(
                0, getIndexOfElement(toElement, inclusive, false) + 1), comparator);
    }

    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive) {
        return new ArraySet<>(data.subList(
                getIndexOfElement(fromElement, inclusive, true), data.size()), comparator);
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return headSet(toElement, false);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return tailSet(fromElement, true);
    }

    private int getIndexOfElement(T element, boolean inclusive, boolean fromHead) {
        int index = Collections.binarySearch(data, element, comparator);
        // found exact key
        if (index >= 0) {
            if (fromHead) {
                // ++ because we need strictly greater
                return inclusive ? index : ++index;
            } else {
                // -- because we need strictly less
                return inclusive ? index : --index;
            }
        }
        // not found exact key, place where key belongs to: -index - 1
        index = -index - 1;
        if (fromHead) {
            return index;
        }
        // -1 because we go from tail and need to take previous element
        else {
            return index - 1;
        }


    }

    private void checkEmptiness() throws NoSuchElementException {
        if (data.isEmpty()) {
            throw new NoSuchElementException();
        }
    }

}
