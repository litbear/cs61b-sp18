package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdOut;

public class MergeX {

    private MergeX() {}

    // 执行插入排序的区间阈值
    private static final int CUTOFF = 7;

    private static Comparable[] aux;


    /**
     * 对数组的指定区间进行插入排序
     *
     * @param a 待排序数组
     * @param lo 区间下界
     * @param hi 区间上界
     */
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for(int i = lo; i <= hi; i++)
            for (int j = i; j > lo && Sort.less(a[j], a[j - 1]); j--)
                Sort.exch(a, j, j - 1);
        assert Sort.isSorted(a, lo, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        assert Sort.isSorted(a, lo, mid);
        assert Sort.isSorted(a, mid + 1, hi);
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(Sort.less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

        assert Sort.isSorted(a, lo, hi);
    }


    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        aux = null;
    }

    private static void sort(Comparable[] a, int lo, int hi) {

        // 这句没必要了
        // if (hi <= lo) return;

        // 使用插入排序对短区间进行优化
        if (hi <= lo + CUTOFF) {
            insertionSort(a, lo, hi);
            assert Sort.isSorted(a, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(a, lo, mid);
        sort(a, mid + 1, hi);

        // 对已经有序的数组放弃归并，直接复制
        if (Sort.less(a[mid], a[mid + 1])) {
            return;
        }

        merge(a, lo, mid, hi);
    }



}
