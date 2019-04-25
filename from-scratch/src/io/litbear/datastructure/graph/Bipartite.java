package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 检测指定的图是否是二分图
 * 检测方式基于命题的充要条件: 对于无向图 G 至少有两个顶点，且其所有回路的长度均为偶数。
 *
 * 在实现中，认为小于三个顶点的图都是二分图，且对于有多个子图的图，必须所有子图都是二分图
 */
public class Bipartite {
    private boolean isBipartite;   // 表示该图是否是二分图
    private boolean[] color;       // color[v] 表示v顶点的颜色
    private boolean[] marked;      // marked[v] = true iff v has been visited in DFS
    private int[] edgeTo;          // edgeTo[v] = last edge on path to v
    private Stack<Integer> cycle;  // odd-length cycle

    public Bipartite(Graph G) {
        color = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int i = 0; i < G.V() && !isBipartite; i++) {
            if (!marked[i]) {
                dfs(G, i);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {

            // 放在当前for循环外面也行
            if (cycle != null) return;

            if (!marked[w]) {
                color[w] = !color[v];
                edgeTo[w] = v;
                dfs(G, w);
                // 整个分支用于判断图中是否有顶点个数为奇数的环
                // w != edgeTo[v] 用于判断是否有环
                // 这步判断是没有必要的，因为如果没有环，
                // 即w == edgeTo[v]，则颜色必然不想等
            } else if (/* w != edgeTo[v] &&*/ color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<>();
                cycle.push(w);
                for(int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    public boolean isBipartite () {
        return isBipartite;
    }

    /**
     * 返回当前图内 顶点个数为奇数的环
     * 如这种环不存在，即图是二分的，则返回null
     *
     * @return
     */
    public Iterable<Integer> oddCycle() {
        return cycle;
    }

    /**
     * 如果当前图是二分图则返回指定顶点的颜色，否则抛出异常
     *
     * @param v 指定顶点
     * @return 指定顶点的颜色
     * @throws UnsupportedOperationException 如果不是二分图 则抛出异常
     */
    public boolean color(int v) {
        validateVertex(v);
        if (!isBipartite) {
            throw new UnsupportedOperationException("graph is not bipartite");
        }
        return color[v];
    }


    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        int V1 = Integer.parseInt(args[0]);
        int V2 = Integer.parseInt(args[1]);
        int E  = Integer.parseInt(args[2]);
        int F  = Integer.parseInt(args[3]);

        // create random bipartite graph with V1 vertices on left side,
        // V2 vertices on right side, and E edges; then add F random edges
        Graph G = GraphGenerator.bipartite(V1, V2, E);
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V1 + V2);
            int w = StdRandom.uniform(V1 + V2);
            G.addEdge(v, w);
        }

        StdOut.println(G);


        Bipartite b = new Bipartite(G);
        if (b.isBipartite()) {
            StdOut.println("Graph is bipartite");
            for (int v = 0; v < G.V(); v++) {
                StdOut.println(v + ": " + b.color(v));
            }
        }
        else {
            StdOut.print("Graph has an odd-length cycle: ");
            for (int x : b.oddCycle()) {
                StdOut.print(x + " ");
            }
            StdOut.println();
        }
    }

}
