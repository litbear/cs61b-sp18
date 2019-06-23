package io.litbear.datastructure.heap;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMaxPQ<Key extends Comparable<Key>> implements IndexMaxPQInterface<Key>, Iterable<IndexMaxPQ.Entry> {

    private int maxN; // 容量
    private int n; // 当前个数
    private int[] pq; // pq[二叉堆索引] = 关联索引
    private int[] qp; // qp[关联索引] = 二叉堆索引
    private Key[] keys; // keys[关联索引]

    public IndexMaxPQ(int maxN) {
        this.maxN = maxN;
        n = 0;
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        keys = (Key[]) new Comparable[maxN + 1];
        for (int i = 0; i < maxN + 1; i++)
            qp[i] = -1;
    }

    // public API

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();

        return qp[i] != -1;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");

        n++;
        pq[n] = i;
        qp[i] = n;
        keys[i] = key;
        swim(n);
    }

    @Override
    public int maxIndex() {
        return pq[1];
    }

    @Override
    public Key maxKey() {
        return keys[pq[1]];
    }

    @Override
    public Key delMax() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");

        int maxIndex = pq[1];
        Key maxKey = keys[maxIndex];

        exch(1, n--);
        sink(1);
        qp[maxIndex] = -1;
        keys[maxIndex] = null;
        pq[n + 1] = -1; // 冗余
        return maxKey;
    }

    @Override
    public Key keyOf(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new IllegalArgumentException("index is not in the priority queue");

        return keys[i];
    }

    @Override
    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new IllegalArgumentException("index is not in the priority queue");

        int heapIndex = qp[i];
        keys[i] = key;
        sink(heapIndex);
        swim(heapIndex);
    }

    @Override
    public void delete(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new IllegalArgumentException("index is not in the priority queue");

        int heapIndex = qp[i];
        exch(heapIndex, n--);
        sink(heapIndex);
        swim(heapIndex);
        keys[i] = null;
        qp[i] = -1;
        pq[n + 1] = -1; // 冗余
    }

    @Override
    public Iterator<Entry> iterator() {
        return new HeapIterator();
    }

    static final class Entry<Key> {
        private Integer index;
        private Key key;

        public Entry() {
        }

        public Entry(Integer index, Key key) {
            this.index = index;
            this.key = key;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }
    }

    public class HeapIterator implements Iterator<Entry> {

        private IndexMaxPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMaxPQ<>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Entry next() {
            if (!hasNext()) throw new NoSuchElementException();
            return new Entry(copy.maxIndex(), copy.delMax());
        }
    }

    // utils

    /**
     * 以二叉堆索引比较两个元素的大小
     *
     * @param i 二叉堆索引
     * @param j 二叉堆索引
     * @return 是否小于
     */
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    /**
     * 以二叉堆索引交换两个元素
     *
     * @param i 二叉堆索引
     * @param j 二叉堆索引
     */
    private void exch(int i, int j) {
        int tempIndex = pq[i];
        pq[i] = pq[j];
        pq[j] = tempIndex;

        // 整理qp
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * 下沉二叉堆索引
     *
     * @param i 二叉堆索引
     */
    private void sink(int i) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(j, j + 1)) j++;
            if (less(j, i)) break;
            exch(i, j);
            i = j;
        }
    }

    /**
     * 上浮二叉堆索引
     *
     * @param i 二叉堆索引
     */
    private void swim(int i) {
        while (i > 1 && less(i / 2, i)) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    // test suite

    /**
     * Unit tests the {@code IndexMaxPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

        IndexMaxPQ<String> pq = new IndexMaxPQ<>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (Entry entry : pq) {
            StdOut.println(entry.getIndex() + " " + entry.getKey());
        }

        StdOut.println();

        // increase or decrease the key
        for (int i = 0; i < strings.length; i++) {
            if (StdRandom.uniform() < 0.5)
                pq.changeKey(i, strings[i] + strings[i]);
            else
                pq.changeKey(i, strings[i].substring(0, 1));
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.maxIndex();
            String key = pq.delMax();
            StdOut.println(i + " " + key);
        }
        StdOut.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete them in random order
        int[] perm = new int[strings.length];
        for (int i = 0; i < strings.length; i++)
            perm[i] = i;
        StdRandom.shuffle(perm);
        for (int i = 0; i < perm.length; i++) {
            String key = pq.keyOf(perm[i]);
            pq.delete(perm[i]);
            StdOut.println(perm[i] + " " + key);
        }

    }
}
