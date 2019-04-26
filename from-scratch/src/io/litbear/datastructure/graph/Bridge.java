package io.litbear.datastructure.graph;


import edu.princeton.cs.algs4.StdOut;

public class Bridge {
    private int bridges;      // bridges 的个数
    private int cnt;          // counter
    private int[] pre;        // pre[v] = 在深度优先搜索中顶点v的访问顺序号
    private int[] low;        // low[v] = 与顶点v相连的所有节点中最小的访问序号

    public Bridge(Graph G) {
        low = new int[G.V()];
        pre = new int[G.V()];
        // 初始化pre，即访问序号数组，-1为未被访问
        for (int v = 0; v < G.V(); v++)
            low[v] = -1;
        for (int v = 0; v < G.V(); v++)
            pre[v] = -1;

        for (int v = 0; v < G.V(); v++)
            if (pre[v] == -1)
                dfs(G, v, v);
    }

    /**
     * 显然，连通分量个数为桥数+1
     *
     * @return 图中连通分量个数
     */
    public int components() { return bridges + 1; }

    /**
     * 递归深度优先搜索
     *
     * @param G 待操作图
     * @param u v节点的父节点
     * @param v 待操作节点
     */
    private void dfs(Graph G, int u, int v) {
        pre[v] = cnt++;
        // 相邻节点最小的访问序号初始化为自身的访问序号
        low[v] = pre[v];
        for (int w : G.adj(v)) {
            // pre[w] == -1 即为未被访问的节点
            if (pre[w] == -1) {
                dfs(G, v, w);
                // 递归后执行
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == pre[w]) {
                    StdOut.println(v + "-" + w + " is a bridge");
                    bridges++;
                }
            }

            // update low number - ignore reverse of edge leading to v
            else if (w != u)
                low[v] = Math.min(low[v], pre[w]);
        }
    }

    // test client
    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        Graph G = GraphGenerator.simple(V, E);
        StdOut.println(G);

        Bridge bridge = new Bridge(G);
        StdOut.println("Edge connected components = " + bridge.components());
    }


}
