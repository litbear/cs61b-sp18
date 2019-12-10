package io.litbear.datastructure.digraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class NonrecursiveDirectedDFS {
    private boolean[] marked; // 记录相应节点是否被访问
    private int count; // 记录可访问节点数量

    // constructor
    public NonrecursiveDirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        Iterator<Integer>[] adjs = new Iterator[G.V()];
        for (int i = 0; i < G.V(); i++)
            adjs[i] = G.adj(i).iterator();
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        marked[s] = true;
        count++;
        while (!stack.isEmpty()) {
            Integer v = stack.peek();
            if (adjs[v].hasNext()) {
                int w = adjs[v].next();
                if (!marked[w]) {
                    marked[w] = true;
                    count++;
                    stack.push(w);
                }
            } else {
                stack.pop();
            }
        }
    }

    // api
    public int count(){
        return count;
    }
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    // validator
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code NonrecursiveDirectedDFS} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        int s = Integer.parseInt(args[1]);
        NonrecursiveDirectedDFS dfs = new NonrecursiveDirectedDFS(G, s);
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v))
                StdOut.print(v + " ");
        StdOut.println();
    }

}
