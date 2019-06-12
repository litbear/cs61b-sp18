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
                for (int j = i; j >= h && less(a[j], a[j - h]); j-=h) {
                    exch(a, j, j - h);
                }
            }
            // 当h为1时，就是插入排序
            assert isHsorted(a, h);
            h = h / 3;
        }
        assert isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        int h = 1;
        while (h <= N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = 1; i < N; i ++) {
                for (int j = i; j >= h && less(comparator, a[j], a[j - h]); j-=h) {
                    exch(a, j, j - h);
                }
            }
            assert isHsorted(a, comparator, h);
            h = h / 3;
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

    // is the array h-sorted?
    private static boolean isHsorted(Comparable[] a, int h) {
        // 从h开始没什么不对的，因为循环内判断的是a[i]与a[i-h]的大小，可以触及h以前的元素
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
    }

    private static boolean isHsorted(Object[] a, Comparator comparator, int h) {
        for (int i = h; i < a.length; i++)
            if (less(comparator, a[i], a[i-h])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        // asc
        sort(a);
        show(a);

        // desc
        Comparator<String> desc = (String o1, String o2) -> -o1.compareTo(o2);
        // TODO lambda直接写在参数里要怎么搞？
        sort(a, desc);
        show(a);
    }
}
