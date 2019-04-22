package io.litear.datastructure.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstSearch {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;           // number of vertices connected to s

    /**
     * 使用深度优先搜索算法搜索与指定点相连接的所有节点
     *
     * @param G 指定图
     * @param s 图中的指定节点
     */
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int adj : G.adj(v)) {
            if (!marked[adj]) {
                dfs(G, adj);
            }
        }
    }

    /**
     * 查询指定节点是否与当前节点相连通
     *
     * @param v 查询节点
     * @return 是否连通
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * 返回与当前节点相连通的节点个数(包括自身)
     *
     * @return 连通节点个数
     */
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.count() != G.V()) StdOut.println("NOT connected");
        else StdOut.println("connected");
    }
}
