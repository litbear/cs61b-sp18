package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 连通分量， 对图中每个(未被标记的)节点依次进行深度优先搜索，
 * 以求出子图的数量，并找出每个子图中的节点
 *
 */
public class CC {
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v 根据顶点获取该定点连通分量ID
    private int[] size;         // size[id] = number of vertices in given component 根据连通分量ID获取其所含顶点个数
    private int count;          // number of connected components 当前连通分量序号

    public CC (Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        // 新建一个较长的数组，在深度遍历的同时不断修改每个子图的数量
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
        // 执行深度优先查找后，根据子图数再次遍历
//        size = new int[count];
//        for(int v = 0; v < G.V(); v++) {
//            size[id[v]]++;
//        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

//    --------------------------

    public CC(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    public void dfs(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (Edge e: G.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * 获取指定节点所在的子图ID
     *
     * @param v 指定节点
     * @return 子图ID
     */
    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * 获取指定节点所在的子图的大小
     *
     * @param v 指定节点
     * @return 子图大小
     */
    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * 获取子图的数量
     *
     * @return 子图的数量
     */
    public int count() {
        return count;
    }

    public boolean connected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        CC cc = new CC(G);

        // number of connected components
        int m = cc.count();
        StdOut.println(m + " components");

        // compute list of vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
