package io.litbear.datastructure.digraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DirectedCycle {
    private boolean[] marked;        // marked[v] = has vertex v been marked?
    private int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
    private boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s] && cycle == null) {
                dfs(G, s);
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {

            if (cycle != null) return;

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                /**
                 * 注意，注释内的是错误代码，
                 * 首先，当w在onStack中时，edgeTo[w]很可能并未被赋值过，
                 * 即有向环尚未封口
                 * 可能是初始值0，因此可能造成`edgeTo[0] == 0`的无限循环
                 * for(int x = w; x != v; x = edgeTo[x]) {
                 * cycle.push(x);
                 * }
                 * cycle.push(v);
                 * cycle.push(w);
                 *
                 */
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        // 有必要吗？？
        // onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    // certify that digraph has a directed cycle if it reports one
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


    /**
     * Unit tests the {@code DirectedCycle} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }
}
