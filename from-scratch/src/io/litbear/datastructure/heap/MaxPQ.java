package io.litbear.datastructure.heap;

import java.util.*;
import java.util.function.Consumer;

public class MaxPQ<Key> implements Iterable<Key> {

    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

//    public MaxPQ(Key[] keys) {}

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public void insert(Key x) {
        // resize
        if (n == pq.length - 1) resize(pq.length << 2);

        pq[++n] = x;
        swim(n);
        assert isMaxHeap();
    }

    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        // fire gc
        pq[n+1] = null;
        // resize
        if (n > 0 && n < (pq.length - 1) / 4) resize(pq.length / 2);

        assert isMaxHeap();
        return max;
    }

    // tool function
    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)){
            exch(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 下沉最大堆指定索引节点，以维护堆的平衡
     *
     * 最大堆下沉时，要与两个子节点中最大的子节点进行交换
     *
     * @param k 待操作索引
     */
    private void sink(int k) {
        while (2 * k <= n) {
            // 左子节点
            int j = 2 * k;
            // 根据while条件，等号保证了k至少要有左子节点
            // 此时再约束j<n即要求k也要有右子节点
            // 如果待交换节点k：有右子节点且右子节点对应值比左子节点大，则使用右子节点；
            // 否则继续使用左子节点，即使用两个子节点中的最大值
            if(j < n && less(j, j + 1)) j++;
            // 如果待交换节点k不小于子节点中的最大值，则下沉完成
            if(!less(k, j)) break;
            // 否则更新当前节点继续下沉
            exch(k, j);
            k = j;
        }
    }

    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
//        for (int i = 1; i <= n; i++)
//            temp[i] = pq[n];
        System.arraycopy(pq, 1, temp, 1, n);
        pq = temp;
    }

    // assertion

    public boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int k) {
        if (k > n) return true;
        int left = 2 * k, right = 2 * k + 1;
        if (left <= n && less(k, left)) return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }


    // iterable

    @Override
    public Iterator<Key> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Key> action) {

    }

    @Override
    public Spliterator<Key> spliterator() {
        return null;
    }
}
