package io.litbear.datastructure.sort;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 算法步骤可视化 抄的
 */
public class InsertionBars {
    public static void sort(Double[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j >= 1 && Sort.less(a[j], a[j - 1])) {
                Sort.exch(a, j, j - 1);
                j--;
            }
            show(a, i, j);
        }
    }


    public static void show(Double[] a, int i, int j) {
        StdDraw.setYscale(-a.length + i + 1, i);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < j; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(j, 0, j, a[j]*0.6);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = j+1; k <= i; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = i+1; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]*0.6);
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