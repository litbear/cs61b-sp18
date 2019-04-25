package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

@SuppressWarnings("Duplicates")
public class BipartiteX {

    private boolean isBipartite;   // is the graph bipartite?
    private boolean[] color;       // color[v] gives vertices on one side of bipartition
    private boolean[] marked;      // marked[v] = true iff v has been visited in DFS
    private int[] edgeTo;          // edgeTo[v] = last edge on path to v
    private Queue<Integer> cycle;  // odd-length cycle

    public BipartiteX(Graph G) {
        isBipartite = true;
        color = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int i = 0; i < G.V() && isBipartite; i++) {
            if (!marked[i]) {
                bfs(G, i);
            }
        }
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();

            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    color[w] = !color[v];
                    edgeTo[w] = v;
                    queue.enqueue(w);
                    // 分支判断条件与 Bitpartile.java 同理
                } else if (color[w] == color[v]) {
                    isBipartite = false;
                    /**
                     * 这里分别用了queue和stack两种数据结构
                     * 是因为根据广度优先搜索的性质，从环闭合的定点出发
                     * 直到同一父顶点经历的边数相同(注意出队与入队的性质)
                     */
                    cycle = new Queue<>();
                    Stack<Integer> stack = new Stack<>();
                    int x = v, y = w;
                    while (x != y) {
                        stack.push(x);
                        cycle.enqueue(y);
                        x = edgeTo[x];
                        y = edgeTo[y];
                    }
                    stack.push(x);
                    while (!stack.isEmpty())
                        cycle.enqueue(stack.pop());
                    cycle.enqueue(w);
                    // 这里不能用break，要用return，注意，break仅仅意味着跳出了for循环
                    return;
                }
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



        BipartiteX b = new BipartiteX(G);
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
