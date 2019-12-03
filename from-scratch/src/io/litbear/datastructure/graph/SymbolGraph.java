package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Graph graph;             // the underlying graph

    /**
     * 根据指定的数据文件及分隔符创建符号图
     *
     * @param filename 数据文件，其中每一行以数据分隔符分割为若干段，
     *                 第一段为指定节点，其余段为其相邻节点
     * @param delimiter 字段分隔符
     */
    public SymbolGraph(String filename, String delimiter) {
        st = new ST<String, Integer>();
        In in = new In(filename);

        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for(int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }

        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                graph.addEdge(v, st.get(a[i]));
            }
        }
    }

    /**
     * 检查当前图是否包含指定符号
     *
     * @param s 指定符号
     * @return 是否包含
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     * 根据给定符号查找相应的节点索引
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     * @deprecated Replaced by {@link #indexOf(String)}.
     */
    @Deprecated
    public int index(String s) {
        return st.get(s);
    }

    /**
     * 根据给定符号(字符串)查找相应的节点索引
     *
     * @param s
     * @return
     */
    public int indexOf(String s) {
        return st.get(s);
    }


    /**
     * 根据给定的节点索引查找相应的符号
     *
     * @param v 节点索引
     * @return 符号
     * @throws IllegalArgumentException 索引非法
     */
    @Deprecated
    public String name(int v) {
        return nameOf(v);
    }


    /**
     * 根据给定的节点索引查找相应的符号
     *
     * @param v 节点索引
     * @return 符号
     * @throws IllegalArgumentException 索引非法
     */
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * 返回与当前符号图关联的Graph对象
     *
     * @return 关联的Graph
     */
    @Deprecated
    public Graph G() {
        return graph;
    }

    /**
     * 返回与当前符号图关联的Graph对象
     * @return 关联的Graph
     */
    public Graph graph() {
        return graph;
    }

    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        for(String v: sg.st) {
            StdOut.println(v);
            for (int w : graph.adj(sg.indexOf(v))) {
                StdOut.println("   " + sg.nameOf(w));
            }
        }

    }
}
