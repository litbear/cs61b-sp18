package io.litbear.datastructure.digraph;

import edu.princeton.cs.algs4.StdOut;

public class Topological {
    private Iterable<Integer> order;
    private int[] rank; // rank[v] = nth; vertex's accessed order

    // constructor


    public Topological(Digraph G) {
        DirectedCycle director = new DirectedCycle(G);
        if (!director.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
            rank = new int[G.V()];
            int i = 0;
            for (int v : order)
                rank[v] = i++;
        }
    }

    // 加权有向图的拓扑排序
    public Topological(EdgeWeightedDigraph G) {
        EdgeWeightedDirectedCycle director = new EdgeWeightedDirectedCycle(G);
        if (!director.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
            // 虽然用不上，但为了保证一致性也加上去了
            rank = new int[G.V()];
            int i = 0;
            for (int v : order) {
                rank[v] = i++;
            }
        }
    }

    // API
    public boolean hasOrder() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }

    /**
     * 给定元素位于拓扑排序中的位置，如果无法拓扑排序，则全部返回-1
     * @param v 指定元素
     * @return 拓扑排序中的位置
     */
    public int rank(int v) {
        validateVertex(v);
        return hasOrder() ? rank[v] : -1;
    }



    // util
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = rank.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code Topological} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Topological topological = new Topological(sg.digraph());
        for (int v : topological.order()) {
            StdOut.println(sg.nameOf(v));
        }
    }
}
