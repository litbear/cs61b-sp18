package io.litbear.datastructure.sort;

import java.util.Comparator;

public class BinaryInsertion {
    private BinaryInsertion() {
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            Comparable v = a[i];
            int lo = 0, hi = i;
            // 不能写 lo <= hi ，
            // 一旦lo，hi相等了，则循环再走一遍，
            // 因为循环体内未对相等进行区分，则lo的值又会向右移一位
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                // 不单独考虑相等的情况了
                // 如果选中值比中间值小，则继续向左移动
                if (Sort.less(v, a[mid])) hi = mid;
                // 如果选中值大于等于中间值，则在中间值的右侧插入
                else lo = mid + 1;
            }
            // 所有中间值右侧的元素全部右移一位
            for (int j = i; j > lo; j--)
                Sort.exch(a, j, j - 1);
            a[lo] = v;
            assert Sort.isSorted(a, 0, i);
        }
        assert Sort.isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            Object v = a[i];
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                // 不单独考虑相等的情况了
                if (Sort.less(comparator, v, a[mid])) hi = mid;
                else lo = mid + 1;
            }
            for (int j = i; j > lo && Sort.less(comparator, a[j], a[j - 1]); j--)
                Sort.exch(a, j, j - 1);
            a[lo] = v;
            assert Sort.isSorted(a, comparator, 0, i);
        }
        assert Sort.isSorted(a, comparator);
    }
}
