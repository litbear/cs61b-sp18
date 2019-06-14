package io.litbear.datastructure.sort;

import java.util.Comparator;

public class Merge {

    // 为避免在递归的sort方法中不断新建aux数组，将其设为冬天变量
    private static Comparable[] aux;
    private static Object[] auxObject;

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 前置条件 待归并的区间由两个有序子区间构成
        assert Sort.isSorted(a, lo, mid);
        assert Sort.isSorted(a, mid+1, hi);
        int i = lo, j = mid + 1;

        // 将指定范围内的元素复制到辅助数组
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if(Sort.less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

        // 后置条件 归并后，整个区间变得有序
        assert Sort.isSorted(a, lo, hi);

    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        aux = null;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // 递归终点
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    // ---------------------------------

    private static void merge(Object[] a, Comparator comparator, int lo, int mid, int hi) {
        // 前置条件 待归并的区间由两个有序子区间构成
        assert Sort.isSorted(a, comparator, lo, mid);
        assert Sort.isSorted(a, comparator,mid+1, hi);
        int i = lo, j = mid + 1;

        // 将指定范围内的元素复制到辅助数组
        for (int k = lo; k <= hi; k++)
            auxObject[k] = a[k];


        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = auxObject[j++];
            else if (j > hi) a[k] = auxObject[i++];
            else if(Sort.less(comparator, auxObject[i], auxObject[j])) a[k] = auxObject[i++];
            else a[k] = auxObject[j++];
        }

        // 后置条件 归并后，整个区间变得有序
        assert Sort.isSorted(a, comparator, lo, hi);
    }

    public static void sort(Object[] a, Comparator comparator) {
        auxObject = new Object[a.length];
        sort(a, comparator, 0, a.length - 1);
        auxObject = null;
    }

    public static void sort(Object[] a, Comparator comparator, int lo, int hi) {
        // 递归终点
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, comparator, lo, mid);
        sort(a, comparator, mid + 1, hi);
        merge(a, comparator, lo, mid, hi);
    }
}
