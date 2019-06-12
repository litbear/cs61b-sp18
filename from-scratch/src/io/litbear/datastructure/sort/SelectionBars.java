package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 算法步骤可视化 抄的
 */
public class SelectionBars {

    public static void sort(Double[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (Sort.less(a[j], a[min])) min = j;
            show(a, i, min);
            Sort.exch(a, i, min);
        }
    }

    private static void show(Double[] a, int i, int min) {
        StdDraw.setYscale(-a.length + i + 1, i);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < i; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = i; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(min, 0, min, a[min]*0.6);
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setCanvasSize(160, 640);
        StdDraw.setXscale(-1, n + 1);
        StdDraw.setPenRadius(0.006);
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(0.0, 1.0);
        sort(a);
    }

}
