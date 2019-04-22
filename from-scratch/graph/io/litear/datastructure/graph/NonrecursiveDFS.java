package io.litear.datastructure.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;


public class NonrecursiveDFS {
    private boolean[] marked;  // marked[v] = is there an s-v path?
    private int count;

    public NonrecursiveDFS(Graph G, int s) {
        marked = new boolean[G.V()];

        Iterator<Integer>[] adj = new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adj(v).iterator();
        }

        Stack<Integer> stack = new Stack<>();
        marked[s] = true;
        stack.push(s);
        count++;
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                if (!marked[w]) {
                    count++;
                    marked[w] = true;
                    stack.push(w);
                }
            } else {
                stack.pop();
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }


    public int count() {
        return 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        NonrecursiveDFS dfs = new NonrecursiveDFS(G, s);
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v))
                StdOut.print(v + " ");
        StdOut.println();

        StdOut.println();
        if (dfs.count() != G.V()) StdOut.println("NOT connected");
        else StdOut.println("connected");
    }

}
