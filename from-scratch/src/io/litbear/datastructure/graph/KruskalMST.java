package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.*;

public class KruskalMST implements MST {

    private double weight;                    // weight of MST
    private Queue<Edge> mst = new Queue<>();  // edges in MST

    public KruskalMST(EdgeWeightedGraph G) {
        // 使用所有边构建最小堆
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge edge : G.edges()) {
            pq.insert(edge);
        }
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
            weight += e.weight();
        }

        assert check(G);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }


}
