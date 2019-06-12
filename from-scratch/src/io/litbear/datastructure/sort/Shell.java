package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Shell {
    // 禁止实例化
    private Shell() {}

    // 核心排序方法


    /**
     * 按默认排序逻辑排序
     * @param a 待排序数组
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h <= N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = 1; i < N; i ++) {
                for (int j = i; j >= h && Sort.less(a[j], a[j - h]); j-=h) {
                    Sort.exch(a, j, j - h);
                }
            }
            // 当h为1时，就是插入排序
            assert isHsorted(a, h);
            h = h / 3;
        }
        assert Sort.isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        int h = 1;
        while (h <= N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = 1; i < N; i ++) {
                for (int j = i; j >= h && Sort.less(comparator, a[j], a[j - h]); j-=h) {
                    Sort.exch(a, j, j - h);
                }
            }
            assert isHsorted(a, comparator, h);
            h = h / 3;
        }
        assert Sort.isSorted(a, comparator);
    }

    // is the array h-sorted?
    private static boolean isHsorted(Comparable[] a, int h) {
        // 从h开始没什么不对的，因为循环内判断的是a[i]与a[i-h]的大小，可以触及h以前的元素
        for (int i = h; i < a.length; i++)
            if (Sort.less(a[i], a[i-h])) return false;
        return true;
    }

    private static boolean isHsorted(Object[] a, Comparator comparator, int h) {
        for (int i = h; i < a.length; i++)
            if (Sort.less(comparator, a[i], a[i-h])) return false;
        return true;
    }
}
