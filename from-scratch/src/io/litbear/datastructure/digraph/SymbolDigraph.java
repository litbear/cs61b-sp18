package io.litbear.datastructure.digraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolDigraph {
    private ST<String, Integer> st ;
    private String[] keys;
    private Digraph digraph;

    public SymbolDigraph(String filename, String delimiter) {
        st = new ST<>();

        // 第一步遍历，生成符号表
        In in = new In(filename);
        while (in.hasNextLine()) {
            String[] strings = in.readLine().split(delimiter);
            for (int i = 0; i < strings.length; i++) {
                if (!st.contains(strings[i])) st.put(strings[i], st.size());
            }
        }

        keys = new String[st.size()];
        for(String name : st.keys())
            keys[st.get(name)] = name;

        // 第二部遍历 生成有向图
        digraph = new Digraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] strings = in.readLine().split(delimiter);
            int v = st.get(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                int w = st.get(strings[i]);
                digraph.addEdge(v, w);
            }
        }
    }

    // API
    public boolean contains(String s) {
        return st.contains(s);
    }

    public int indexOf(String s) {
        if (!contains(s)) throw new IllegalArgumentException("vertex " + s + " is not in symbol table");
        return st.get(s);
    }

    public String nameOf(int i) {
        validateVertex(i);
        return keys[i];
    }

    public Digraph digraph() {
        return digraph;
    }

    // utils
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = digraph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


    /**
     * Unit tests the {@code SymbolDigraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Digraph graph = sg.digraph();
        while (!StdIn.isEmpty()) {
            String t = StdIn.readLine();
            for (int v : graph.adj(sg.indexOf(t))) {
                StdOut.println("   " + sg.nameOf(v));
            }
        }
    }
}
