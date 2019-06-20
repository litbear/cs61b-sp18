package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

public class QuickKR {

    private QuickKR() {
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert Sort.isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        Sort.exch(a, lo, (lo + hi) / 2);
        int last = lo;
        for (int i = lo + 1; i <= hi; i++)
            if (Sort.less(a[i], a[lo])) Sort.exch(a, ++last, i);
        Sort.exch(a, lo, last);
        sort(a, lo, last - 1);
        sort(a, last + 1, hi);
    }

    public static void sort(Object[] a, Comparator comparator) {
        StdRandom.shuffle(a);
        sort(a, comparator, 0, a.length - 1);
        assert Sort.isSorted(a, comparator);
    }

    private static void sort(Object[] a, Comparator comparator, int lo, int hi) {
        if (hi <= lo) return;

        Sort.exch(a, lo, (hi + lo) / 2);
        int last = lo;
        for (int i = lo + 1; i <= hi; i++)
            if(Sort.less(comparator, a[i], a[lo])) Sort.exch(a, ++last, i);
        Sort.exch(a, lo, last);
        sort(a, comparator, lo, last - 1);
        sort(a, comparator, last + 1, hi);
    }
}
