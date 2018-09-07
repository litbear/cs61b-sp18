package io.litear.datastructure.set;

public class WeightedQuickUnionDS implements DisjointSet {

    private int[] weight;
    private int[] parent;

    public WeightedQuickUnionDS(int n) {
        weight = new int[n];
        parent = new int[n];

        for (int i = 0; i < n; i += 1) {
            weight[i] = 1;
            parent[i] = i;
        }
    }


    /**
     * 查找元素在集合内的根节点
     *
     * @param e 待查找元素索引
     * @return 根节点
     */
    private int findRoot(int e) {
        while (e != parent[e]){
            e = parent[e];
        }
        return e;
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
