package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class MergeBU {

    private MergeBU() {}

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert Sort.isSorted(a, lo, mid);
        assert Sort.isSorted(a, mid + 1, hi);
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (Sort.less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

        assert Sort.isSorted(a, lo, hi);
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        // size < N 保证在最后一次循环归并出数组长度
        for (int size = 1; size < N; size *= 2) // size 子数组大小
            // lo + size < N 即留出最后一个子数组，最后一个字数组长度小于等于size
            // lo += 2*size 取子数组的奇数项
            for (int lo = 0; lo + size < N; lo += 2*size) // lo 子数组中奇数项的开头
                merge(a, aux, lo, lo + size - 1, Math.min(lo + 2*size - 1, N - 1));
    }

    // Comparator as arguments

    private static void merge(
            Object[] a, Object[] aux, Comparator comparator, int lo, int mid, int hi) {
        assert Sort.isSorted(a, comparator, lo, mid);
        assert Sort.isSorted(a, comparator, mid + 1, hi);

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (Sort.less(comparator, aux[i], aux[j]))
                a[k] = aux[i++];
            else a[k] = aux[j++];
        }

        assert Sort.isSorted(a, comparator, lo, hi);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        Object[] aux = new Object[N];
        for (int size = 1; size < N; size *= 2)
            for (int lo = 0; lo + size < N; lo += 2*size)
                merge(a, aux, comparator, lo, lo + size - 1, Math.min(lo + 2*size - 1, N - 1));
    }

}
