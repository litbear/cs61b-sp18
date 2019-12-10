package io.litbear.datastructure.digraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V; // 定点数
    private int E; // 边数
    private Bag<Integer>[] adj; // 邻接矩阵
    private int[] indegree; // indegree[v] 顶点v的入度


    // constructor

    /**
     * 以指定节点数初始化图
     * @param V 节点数
     */
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<Integer>();
    }

    /**
     * 从格式化文件中读取图
     * @param in 文件输入流
     */
    public Digraph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
            indegree = new int[V];
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++)
                adj[v] = new Bag<Integer>();

            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }


    }

    /**
     * 深度复制给定图
     * @param G 给定图
     */
    public Digraph(Digraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++)
            this.indegree[v] = G.indegree(v);
        for (int v = 0; v < G.V(); v++) {
            Stack<Integer> reverse = new Stack<>();
            for (int w : G.adj(v))
                reverse.push(w);
            for (int w : reverse)
                adj[v].add(w);
        }
    }

    // api
    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        // 注意是有向图，不做双向添加
        adj[v].add(w);
        // 添加相应顶点入度
        indegree[w]++;
        E++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * 求当前图的反向图，拓扑排序时使用
     * @return 当前图的反向图
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj[v])
                reverse.addEdge(w, v);
        return reverse;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    // utils
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // test suite
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
        StdOut.println("------------");
        StdOut.println(G.reverse());
    }
}
