package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 广度优先搜索 并找出路径
 */
public class BreadthFirstPaths {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = u means the edge from u edge to v
    private int[] distTo; // distTo[v] = x means there are x edges in shortest path from s to v

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        bfs(G, s);
    }

    /**
     * 以指定节点为起点，广度优先搜索指定图
     *
     * @param G 待搜索图
     * @param s 指定起点
     */
    @SuppressWarnings("Duplicates")
    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        distTo[s] = 0;
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for(int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[v] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public BreadthFirstPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        bfs(G, sources);
    }

    /**
     * 以多个指定的节点为起点，广度优先搜索指定图
     *
     * @param G 待搜索图
     * @param sources 指定的多个起点
     */
    @SuppressWarnings("Duplicates")
    private void bfs (Graph G, Iterable<Integer> sources) {
        Queue<Integer> queue = new Queue<>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            queue.enqueue(s);
        }

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for(int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[v] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        /**
         * 可以用for循环改写
         * 注意，因为本类允许有多个起始节点，因此while循环的终点为"与起点相隔0条路径的点"
         * 另外注意，使用`edgeTo[x] == 0` 去判断，因为序号为0的节点是有实际意义的
         */
        int x = v;
        while (distTo[x] != 0) {
            stack.push(x);
            x = edgeTo[x];
        }
        stack.push(x);
        return stack;
    }

}
