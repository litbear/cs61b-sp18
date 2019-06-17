package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Sort {

    // 工具函数
    public static boolean less(Comparable v, Comparable w) {
        if (v == null || w == null) return false;
        return v.compareTo(w) < 0;
    }

    public static boolean less(Comparator comparator, Object v, Object w) {
        if (v == null || w == null) return false;
        return comparator.compare(v, w) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // Assertion 断言工具函数
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
//         asc
//        Selection.sort(a);
//        Insertion.sort(a);
//        Shell.sort(a);
//        BinaryInsertion.sort(a);
//        Merge.sort(a);
//        MergeX.sort(a);
//        MergeBU.sort(a);
        Quick.sort(a);
        show(a);

//         desc
        Comparator<String> desc = (String o1, String o2) -> -o1.compareTo(o2);
        // TODO lambda直接写在参数里要怎么搞？
//        Selection.sort(a, desc);
//        Insertion.sort(a, desc);
//        Shell.sort(a, desc);
//        BinaryInsertion.sort(a, desc);
//        Merge.sort(a, desc);
//        MergeX.sort(a, desc);
//        MergeBU.sort(a, desc);
//        show(a);
    }
}
