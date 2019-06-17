package io.litbear.datastructure.sort;

import java.util.Comparator;

public class QuickMedian3 {
    private static final int CUTOFF = 8;

    private QuickMedian3() {}

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
        assert Sort.isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // if (lo >= hi) return;
        if (lo + CUTOFF - 1 >= hi) {
            insertionSort(a, lo, hi);
            return;
        }

        int m = median3(a, lo, lo + (hi - lo + 1) / 2, hi);
        Sort.exch(a, lo, m);

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        assert Sort.isSorted(a, lo, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (Sort.less(a[++i], a[lo])) if (i == hi) break;
            while (Sort.less(a[lo], a[--j])) if (j == lo) break; // 冗余
            if (i >= j) break;
            Sort.exch(a, i, j);
        }
        Sort.exch(a, lo, j);
        return j;
    }

    /**
     * 在给定的数组和三个索引中，求对应值中位数的索引
     * @param a 给定数组
     * @param i i
     * @param j j
     * @param k k
     * @return 中位数的索引
     */
    private static int median3(Comparable[] a, int i, int j, int k) {
        return Sort.less(a[i], a[j]) ?
                (Sort.less(a[j], a[k]) ? j : (Sort.less(a[i], a[k]) ? k : i)) :
                (Sort.less(a[i], a[k]) ? i : (Sort.less(a[j], a[k]) ? j : k));
    }

    /**
     * 对给定数组的给定区间进行插入排序
     * @param a 待排序数组
     * @param lo 区间下界
     * @param hi 区间上界
     */
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && Sort.less(a[j], a[j-1]); j--)
                Sort.exch(a, j, j-1);
        assert Sort.isSorted(a, lo, hi);
    }

    // comparator as arguments

    public static void sort(Object[] a, Comparator comparator) {
        sort(a, comparator, 0, a.length - 1);
    }

    private static void sort(Object[] a, Comparator comparator, int lo, int hi) {
        if (lo + CUTOFF - 1 >= hi) {
            insertionSort(a, comparator, lo, hi);
            return;
        }

        int mid = median3(a, comparator, lo, lo + (hi - lo) / 2, hi);
        Sort.exch(a, lo, mid);
        int j = partition(a, comparator, lo, hi);
        sort(a, comparator, lo, j - 1);
        sort(a, comparator, j + 1, hi);
        assert Sort.isSorted(a, comparator, lo, hi);
    }

    private static int partition(Object[] a, Comparator comparator, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (Sort.less(comparator, a[++i], a[lo])) if (i == hi) break;
            while (Sort.less(comparator, a[lo], a[--j])) if (j == lo) break; // 冗余
            if (i >= j) break;
            Sort.exch(a, i, j);
        }
        Sort.exch(a, j, lo);
        return j;
    }

    private static int median3(Object[] a, Comparator comparator, int i, int j, int k) {
        return Sort.less(comparator, a[i], a[j]) ?
                Sort.less(comparator, a[j], a[k]) ? k : (Sort.less(comparator, a[i], a[k]) ? k : i) :
                Sort.less(comparator, a[i], a[k]) ? i : (Sort.less(comparator, a[j], a[k]) ? k : j);

    }

    private static void insertionSort(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && Sort.less(comparator, a[j], a[j - 1]); j--) {
                Sort.exch(a, j, j - 1);
            }
            Sort.isSorted(a, comparator, lo, i);
        }
        assert Sort.isSorted(a, comparator, lo, hi);
    }

}
