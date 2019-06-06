package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Selection {

    // 禁止实例化
    private Selection() {}

    // 核心排序方法


    /**
     * 按默认排序逻辑排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i; j < N; j++)
                if (less(comparator, a[j], a[min])) min = j;
            exch(a, i, min);
            assert isSorted(a, comparator,0, i);
        }
        assert isSorted(a, comparator);
    }

    // 工具函数

    private static boolean less(Comparator comparator, Object v, Object w) {
        if (v == null || w == null) return false;
        return comparator.compare(v, w) < 0;
    }

    private static boolean less(Comparable v, Comparable w) {
        if (v == null || w == null) return false;
        return v.compareTo(w) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    // assertion
    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }


    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    public static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        show(a);
    }

}
