package io.litbear.datastructure.digraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFS {
    private boolean[] marked;  // marked[v] = true iff v is reachable from source(s)
    private int count;         // number of vertices reachable from source(s)

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        validateVertices(sources);
        for (int s : sources) {
            if (!marked[s]) dfs(G, s);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        count++;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int count() {
        return count;
    }


    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }

    /**
     * Unit tests the {@code DirectedDFS} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // read in digraph from command-line argument
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        // read in sources from command-line arguments
        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 1; i < args.length; i++) {
            int s = Integer.parseInt(args[i]);
            sources.add(s);
        }

        // multiple-source reachability
        DirectedDFS dfs = new DirectedDFS(G, sources);

        // print out vertices reachable from sources
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) StdOut.print(v + " ");
        }
        StdOut.println();
    }
}
