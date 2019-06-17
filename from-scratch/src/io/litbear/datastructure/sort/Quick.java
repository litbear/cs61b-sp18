package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert Sort.isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        int j = partition(a, lo, hi);
        sort(a, lo, j);
        sort(a, j + 1, hi);
        assert Sort.isSorted(a, lo, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable v = a[lo];
        int i = lo, j = hi + 1;

        while (true) {
            while (Sort.less(a[++i], v)) if (i == hi) break;
            while (Sort.less(v, a[--j])) if (j == lo) break; // 这一句是冗余的
            if (i >= j) break; // 两个指针交汇
            Sort.exch(a, i, j);
        }
        Sort.exch(a, lo, j);
        return j;
    }

    /**
     * 使用partition在非有序数组中快速找到升序第index个元素对应的值
     * @param a 待查找数组
     * @param index 指定索引
     * @return 指定索引对应的值
     */
    public static Comparable select(Comparable[] a, int index) {
        if (index < 0 || index > a.length - 1)
            throw new IndexOutOfBoundsException("index is not between 0 and " + a.length + ": " + index);
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if      (i > index) hi = i - 1;
            else if (i < index) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }
}
