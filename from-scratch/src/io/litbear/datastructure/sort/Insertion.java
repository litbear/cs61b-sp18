package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdIn;

import java.util.Comparator;

public class Insertion {

    // 禁止实例化
    private Insertion() {}

    // 核心排序方法


    /**
     * 按默认排序逻辑排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && Sort.less(a[j], a[j-1]); j--)
                Sort.exch(a, j, j-1);
            assert Sort.isSorted(a, 0, i);
        }
        assert Sort.isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && Sort.less(comparator, a[j], a[j - 1]); j--) {
                Sort.exch(a, j, j - 1);
            }
            assert Sort.isSorted(a, comparator, 0, i);
        }
        assert Sort.isSorted(a, comparator);
    }



}
