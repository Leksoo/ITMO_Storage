package ru.ifmo.rain.feponov.arrayset;

import java.lang.reflect.Array;
import java.util.*;

public class Test {
    public static void main(String[] args) {
//        NavigableSet<Integer> arraySet = new ArraySet<>(Arrays.asList(1, 2, 3, 4, 5));
//        for (int el : arraySet) {
//            System.out.print(el);
//        }
//        System.out.println();
//        arraySet = arraySet.descendingSet();
//        for (int el : arraySet) {
//            System.out.print(el);
//        }
//        System.out.println();
//        arraySet = arraySet.descendingSet();
//        for (int el : arraySet) {
//            System.out.print(el);
//        }
//        System.out.println();
        long start = System.currentTimeMillis();
        int N = 200000;
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0 ; i < N; i++)
            tmp.add(i);

        NavigableSet<Integer> arraySet = new ArraySet<>(tmp);
        for (int i = 0; i < N; i++) {
            arraySet = arraySet.descendingSet();
        }
        for (int el : arraySet) {
            System.out.print(el);
        }
        System.out.println();
        System.out.println((-start + System.currentTimeMillis())/1000);
    }
}
