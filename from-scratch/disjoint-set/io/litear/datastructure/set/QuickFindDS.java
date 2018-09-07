package io.litear.datastructure.set;

import java.util.Arrays;
import java.util.stream.IntStream;

public class QuickFindDS implements DisjointSet {

    private int[] d;

    public QuickFindDS(int n) {
        d = new int[n];
        for(int i = 0; i < n; i += 1){
            d[i] = i;
        }
    }

    @Override
    public void connect(int p, int q) {
        int pSetID = d[p];
        int qSetID = d[q];

        if (qSetID == pSetID) return;

        for (int i = 0; i < d.length; i += 1) {
            if (d[i] == pSetID) {
                d[i] = qSetID;
            }
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return d[p] == d[q];
    }
}
