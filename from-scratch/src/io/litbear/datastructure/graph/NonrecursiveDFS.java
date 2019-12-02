package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;


public class NonrecursiveDFS {
    private boolean[] marked;  // marked[v] = is there an s-v path?
    private int count;

    public NonrecursiveDFS(Graph G, int s) {
        marked = new boolean[G.V()];

        // 构建迭代器数组
        Iterator<Integer>[] iterators = new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            iterators[v] = G.adj(v).iterator();
        }

        // 储存从s到当前顶点的路径
        Stack<Integer> stack = new Stack<>();
        marked[s] = true;
        stack.push(s);
        count++;
        while (!stack.isEmpty()) {
            // 取栈顶元素
            int v = stack.peek();
            if (iterators[v].hasNext()) {
                // 从迭代器数组中取出指定迭代器继续操作
                int w = iterators[v].next();
                if (!marked[w]) {
                    count++;
                    marked[w] = true;
                    // 置于栈顶，走向更深度的遍历
                    stack.push(w);
                }
            } else {
                // 当前迭代器耗尽后弹出对应元素
                stack.pop();
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }


    public int count() {
        return count;
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
