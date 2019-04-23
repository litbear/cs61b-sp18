package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Cycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public Cycle(Graph G) {
//        if(hasSelfLoop(G)) return;
//        if(hasParallelEdges(G)) return;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, -1, s);
            }
        }
    }

    // does this graph have a self loop?
    // side effect: initialize cycle to be self loop
    private boolean hasSelfLoop(Graph G) {
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;
    }

    // does this graph have two parallel edges?
    // side effect: initialize cycle to be two parallel edges
    // 平行边存在性判别
    private boolean hasParallelEdges(Graph G) {
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {

            // check for parallel edges incident to v
            // 一旦在一个邻接列表出现两次同意变量，即判定有平行边
            for (int w : G.adj(v)) {
                if (marked[w]) {
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
                marked[w] = true;
            }

            // reset so marked[v] = false for all v
            // 对于每个邻接列表都要重置marked
            for (int w : G.adj(v)) {
                marked[w] = false;
            }
        }
        return false;
    }

    /**
     * For every visited vertex ‘v’, if there is an adjacent ‘w’
     * such that w is already visited and w is not parent of v,
     * then there is a cycle in graph.
     * <p>
     * 对于所有以访问的节点'v'，如果存在某个相邻节点'w'，已经访问，
     * 且w不是v节点的父节点，则当前图中存在环
     * <p>
     * 这里的"已经访问，且w不是v节点的父节点" 既可以使用递归参数，
     * 也可以使用"w != edgeTo[v]"
     * <p>
     * 该判别方法与搜索方式无关，即，不管是DFS还是BFS都可以
     *
     * @param G 待操作图
     * @param u 父节点
     * @param v 当前节点
     */
    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            // 注意，这里要结束递归
            if (cycle != null) return;


            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, v, w);
            } else if (w != u /* w != edgeTo[v] */) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                // 此处递归停止
            }
        }
    }

    /**
     * @return 当前图内是否存在环
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * @return 返回当前图中存在的(首个)环的迭代器
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    /**
     * Unit tests the {@code Cycle} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("Graph is acyclic");
        }
    }
}
