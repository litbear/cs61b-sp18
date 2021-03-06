package io.litbear.datastructure.digraph;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstOrder {
    private boolean[] marked;
    private int[] pre;
    private int[] post;
    private Queue<Integer> preorder;
    private Queue<Integer> postorder;
    private int preCounter;
    private int postCounter;

    // constructor

    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        pre = new int[G.V()];
        post = new int[G.V()];
        preorder = new Queue<>();
        postorder = new Queue<>();

        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
        assert check();
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;

        pre[v] = preCounter++;
        preorder.enqueue(v);

        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }

        post[v] = postCounter++;
        postorder.enqueue(v);
    }


    // 加权有向图深度优先遍历.
    public DepthFirstOrder(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        pre = new int[G.V()];
        post = new int[G.V()];
        preorder = new Queue<>();
        postorder = new Queue<>();

        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
        assert check();
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;

        pre[v] = preCounter++;
        preorder.enqueue(v);

        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w]) dfs(G, w);
        }

        post[v] = postCounter++;
        postorder.enqueue(v);
    }

    // API
    public int pre(int v) {
        validateVertex(v);
        return pre[v];
    }

    public int post(int v) {
        validateVertex(v);
        return post[v];
    }

    public Iterable<Integer> pre() {
        return preorder;
    }

    public Iterable<Integer> post() {
        return postorder;
    }

    public Iterable<Integer> reversePost() {
        Stack<Integer> stack = new Stack<>();
        for (int v : postorder)
            stack.push(v);
        return stack;
    }


    // utils
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // check that pre() and post() are consistent with pre(v) and post(v)
    private boolean check() {

        // check that post(v) is consistent with post()
        int r = 0;
        for (int v : post()) {
            if (post(v) != r) {
                StdOut.println("post(v) and post() inconsistent");
                return false;
            }
            r++;
        }

        // check that pre(v) is consistent with pre()
        r = 0;
        for (int v : pre()) {
            if (pre(v) != r) {
                StdOut.println("pre(v) and pre() inconsistent");
                return false;
            }
            r++;
        }

        return true;
    }

    // test suite
    /**
     * Unit tests the {@code DepthFirstOrder} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        DepthFirstOrder dfs = new DepthFirstOrder(G);
        StdOut.println("   v  pre post");
        StdOut.println("--------------");
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
        }

        StdOut.print("Preorder:  ");
        for (int v : dfs.pre()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Postorder: ");
        for (int v : dfs.post()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        for (int v : dfs.reversePost()) {
            StdOut.print(v + " ");
        }
        StdOut.println();


    }
}
