package io.litbear.datastructure.set;

public interface DisjointSet {

    /**
     * called union(int p, int q) in Algorithms, 4th Edition
     *
     * @param p element p
     * @param q element q
     */
    void connect(int p, int q);

    /**
     * called connected(int p, int q) in Algorithms, 4th Edition
     *
     * @param p element p
     * @param q element q
     * @return whether two point is connected
     */
    boolean isConnected(int p, int q);
}
