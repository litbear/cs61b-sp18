package io.litear.datastructure.set;

public class WeightedQuickUnionWithPathCompressionDS implements DisjointSet {
    private int[] weight;
    private int[] parent;

    public WeightedQuickUnionWithPathCompressionDS(int n) {
        weight = new int[n];
        parent = new int[n];

        for (int i = 0; i < n; i += 1) {
            weight[i] = 1;
            parent[i] = i;
        }
    }


    /**
     * 查找元素在集合内的根节点，且将该元素的所有祖先节点都指向根节点
     * 递归方式实现
     *
     * @param e 待查找元素索引
     * @return 根节点
     */
    private int findRootRecursive(int e) {
        if (e == parent[e]) {
            return e;
        }
        parent[e] = findRootRecursive(parent[e]);
        return parent[e];
    }

    /**
     * 查找元素在集合内的根节点，且将该元素的所有祖先节点都指向根节点
     * 迭代方式实现
     *
     * @param e 待查找元素索引
     * @return 根节点
     */
    private int findRoot(int e) {
        int root = e;

        while (parent[e] != e){
            root = parent[e];
        }
        while (e != root) {
            int p = parent[e];
            parent[e] = root;
            e = p;
        }
        return root;
    }

    @Override
    public void connect(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (weight[pRoot] > weight[qRoot]) {
            parent[qRoot] = pRoot;
            weight[pRoot] += weight[qRoot];
        } else {
            parent[pRoot] = qRoot;
            weight[qRoot] += weight[pRoot];
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        return qRoot == pRoot;
    }
}
