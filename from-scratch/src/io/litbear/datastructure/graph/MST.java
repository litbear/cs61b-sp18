package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public interface MST {
    final double FLOATING_POINT_EPSILON = 1E-12;

    double weight();

    Iterable<Edge> edges();

    /**
     * 对最小生成树进行验证
     *
     * @param G 无向加权连通图
     * @return 当前生成树是否最优
     */
    // check optimality conditions (takes time proportional to E V lg* V)
    default boolean check(EdgeWeightedGraph G) {

        // check total weight
        // 验证总权重
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", total, weight());
            return false;
        }

        // check that it is acyclic
        // 验证是否无环
        // 如果某两个端点union了两次，则说明有环(至少有平行边)
        UF uf = new UF(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        // 如果发现两个点不相连，则一定不是最小生成树
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        // 暴力验证最小生成树 (没什么卵用吧？建立在平行边的基础上的)
        for (Edge e : edges()) {

            // all edges in MST except e
            // 对生成树中的每条边：
            // 将除了当前边外的其余的边都加入并查集
            uf = new UF(G.V());
            for (Edge f : edges()) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            // 遍历加权图中的所有边，如果有某条从相同两个端点出发，但权重比当前边大的边，则报错
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }

    /**
     * Unit tests the MST data type.
     *
     * @param args the command-line arguments
     */
    static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
//        MST mst = new KruskalMST(G);
//        MST mst = new LazyPrimMST(G);
        MST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
