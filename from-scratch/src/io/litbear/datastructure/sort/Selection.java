package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdIn;
import java.util.Comparator;

public class Selection {

    // 禁止实例化
    private Selection() {}

    // 核心排序方法


    /**
     * 按默认排序逻辑排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i; j < N; j++)
                if (Sort.less(a[j], a[min])) min = j;
            Sort.exch(a, i, min);
            assert Sort.isSorted(a, 0, i);
        }
        assert Sort.isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i; j < N; j++)
                if (Sort.less(comparator, a[j], a[min])) min = j;
            Sort.exch(a, i, min);
            assert Sort.isSorted(a, comparator,0, i);
        }
        assert Sort.isSorted(a, comparator);
    }

}
