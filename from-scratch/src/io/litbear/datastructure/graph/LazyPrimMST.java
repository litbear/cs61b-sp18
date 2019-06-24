package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.*;

public class LazyPrimMST implements MST{


    private double weight;                     // weight of MST
    private Queue<Edge> mst = new Queue<>();  // edges in MST
    private boolean[] marked;
    private MinPQ<Edge> pq = new MinPQ<>();

    /**
     * 针对非连通的无向加权图做了遍历
     * 最终结果为最小生成树(森林)
     *
     * @param G 无向加权图
     */
    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        // 遍历所有连通分量，因此是最小生成森林
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                prim(G, v);
            }
        }
        // check optimality conditions
        assert check(G);
    }

    private void prim(EdgeWeightedGraph G, int s) {
        visit(G, s);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            assert marked[v] || marked[w];
            // MST内部的边，跳过
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        assert !marked[v];
        marked[v] = true;
        for (Edge edge : G.adj(v)) {
            int w = edge.other(v);
            if (!marked[w]) {
                pq.insert(edge);
            }
        }
    }

    public double weight() {
        return weight;
    }

    public Iterable<Edge> edges() {
        return mst;
    }

}
