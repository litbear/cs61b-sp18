package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

public class Quick3way {
    private Quick3way() {
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert Sort.isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        Comparable v = a[lo];
        int lt = lo, gt = hi;
        int i = lt + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) Sort.exch(a, lt++, i++);
            else if (cmp > 0) Sort.exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
        assert Sort.isSorted(a, lo, hi);
    }

    // comparator as arguments

    public static void sort(Object[] a, Comparator comparator) {
        StdRandom.shuffle(a);
        sort(a, comparator, 0, a.length - 1);
        assert Sort.isSorted(a, comparator);
    }

    private static void sort(Object[] a, Comparator comparator, int lo, int hi) {
        if (lo >= hi) return;

        Object v = a[lo];
        int lt = lo, gt = hi, i = lo + 1;
        while (i <= gt) {
            int cmp = comparator.compare(a[i], v);
            if (cmp < 0) Sort.exch(a, lt++, i++);
            else if (cmp > 0) Sort.exch(a, i, gt--);
            else i++;
        }
        sort(a, comparator, lo, lt -1);
        sort(a, comparator, lt + 1, hi);
        assert Sort.isSorted(a, comparator, lo, hi);
    }

}