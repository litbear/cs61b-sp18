package io.litbear.datastructure.graph;


import edu.princeton.cs.algs4.Queue;
import io.litbear.datastructure.heap.IndexMinPQ;

public class PrimMST implements MST {

    private Edge[] edgeTo; // edgeTo[v] 最小生成树外的节点v到最小生成树的最短边
    private double[] distTo; // distTo[v] 最小生成树外的节点v到最小生成树的最短边权重
    private boolean[] marked; // marked[v] 顶点v是否在最小生成树 (是否冗余)
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        // init config
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        // 每个连通分量依次遍历，取得最小生成森林
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) prim(G, v);

        // 合法性检验
        assert check(G);
    }

    private void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.minIndex();
            pq.delMin();
            scan(G, v);
        }
    }

    private void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            // 跳过MST内部边
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.changeKey(w, e.weight());
                } else {
                    pq.insert(w, e.weight());
                }
            }
        }
    }

    @Override
    public double weight() {
        double weight = .0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }

    @Override
    public Iterable<Edge> edges() {
        Queue<Edge> queue = new Queue<>();
        for (int i = 0; i < edgeTo.length; i++)
            if(edgeTo[i] != null) queue.enqueue(edgeTo[i]);
        return queue;
    }
}
