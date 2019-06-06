package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && less(comparator, a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
            assert isSorted(a, comparator, 0, i);
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
