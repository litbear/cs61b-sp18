package io.litbear.datastructure.digraph;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用深度优先搜索，从给定起点开始预处理有向图
 * 判断给定定点是否能从起点到达
 */
public class DepthFirstDirectedPaths {
    private static final int START_SENTINEL = -1;
    private boolean[] marked;
    private int[] edgeTo;

    public DepthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        edgeTo = new int[G.V()];
        dfs(G, START_SENTINEL, s);
    }

    private void dfs(Digraph G, int u, int v) {
        marked[v] = true;
        edgeTo[v] = u;
        for(int w : G.adj(v)) {
            if (!marked[w]) dfs(G, v, w);
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!marked[v]) return null;
        Stack<Integer> stack = new Stack<>();
        while (v != START_SENTINEL) {
            stack.push(v);
            v = edgeTo[v];
        }
        return stack;
    }


    // util
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code DepthFirstDirectedPaths} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        // StdOut.println(G);

        int s = Integer.parseInt(args[1]);
        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }
}
