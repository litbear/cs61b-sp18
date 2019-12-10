package io.litbear.datastructure.digraph;

import edu.princeton.cs.algs4.*;

/**
 * 以非递归，基于队列的算法判断给定有向图是否有环
 * 构造方法时间复杂度最坏情况下与V+E成正比
 */
public class DirectedCycleX {
    private Stack<Integer> cycle;     // the directed cycle; null if digraph is acyclic

    public DirectedCycleX(Digraph G) {

        // 入度数组，储存每个顶点的入度
        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
        }

        // 初始化队列 添加所有入度为0的顶点 即发散顶点
        Queue<Integer> queue = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            if (indegree[v] == 0) queue.enqueue(v);

        /**
         * 沿着所有发散顶点向下走，
         * 沿途所有点如果除了从根结点触发的路径之外，再无其他边指向该定点
         * 则说明该顶点一定不在环上
         */
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0) queue.enqueue(w);
            }
        }

        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        int[] edgeTo = new int[G.V()];
        int root = -1;  // any vertex with indegree >= -1
        for (int v = 0; v < G.V(); v++) {
            // 跳过预处理过的，一定不在环上的点
            if (indegree[v] == 0) continue;
            else root = v;
            for (int w : G.adj(v)) {
                if (indegree[w] > 0) {
                    edgeTo[w] = v;
                }
            }
        }

        if (root != -1) {

            // find any vertex on cycle
            boolean[] visited = new boolean[G.V()];
            while (!visited[root]) {
                visited[root] = true;
                root = edgeTo[root];
            }

            // extract cycle
            cycle = new Stack<Integer>();
            int v = root;
            do {
                cycle.push(v);
                v = edgeTo[v];
            } while (v != root);
            cycle.push(root);
        }

        assert check();
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and {@code null} otherwise.
     * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
     *    and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    /**
     * Does the digraph have a directed cycle?
     * @return {@code true} if the digraph has a directed cycle, {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    // certify that digraph has a directed cycle if it reports one
    @SuppressWarnings("Duplicates")
    private boolean check() {

        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }


        return true;
    }


    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }
}
