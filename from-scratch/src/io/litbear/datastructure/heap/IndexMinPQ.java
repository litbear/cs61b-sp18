package io.litbear.datastructure.heap;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> implements IndexMinPQInterface<Key>, Iterable<IndexMinPQ.Entry>{

    private int maxN; // 初始化容量
    private int n; // 当前元素数量
    private int[] pq; // pq[二叉堆索引] = 关联索引
    private int[] qp; // qp[关联索引] = 二叉堆索引
    private Key[] keys; // keys[关联索引]

    // constructor
    public IndexMinPQ(int maxN) {
        this.maxN = maxN;
        n = 0;
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        keys = (Key[]) new Comparable[maxN + 1];
        for (int i = 0; i < maxN + 1; i++)
            qp[i] = -1;
    }

    // utils

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int tempIndex = pq[i];
        pq[i] = pq[j];
        pq[j] = tempIndex;

        // 整理qp数组
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int i) {
        while (i > 1 && less(i, i / 2)) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    @SuppressWarnings("Duplicates")
    private void sink(int i) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(j + 1, j)) j++;
            if (less(i, j)) break;
            exch(i, j);
            i = j;
        }
    }

    // API

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public boolean contains(int i) {
        if (i < 0 || i>= maxN) throw new IllegalArgumentException();
        return qp[i] != -1;
    }

    @Override
    public int size() {
        return n;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void insert(int i, Key key) {
        if (i < 0 || i>= maxN) throw new IllegalArgumentException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");

        n++;
        pq[n] = i;
        qp[i] = n;
        keys[i] = key;
        swim(n);
    }

    @Override
    public int minIndex() {
        return pq[1];
    }

    @Override
    public Key minKey() {
        return keys[pq[1]];
    }

    /**
     * 注意，这里返回的是key，而不是int类型的index，与示例代码不一致
     *
     * @return Key
     */
    @SuppressWarnings("Duplicates")
    @Override
    public Key delMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");

        int minIndex = pq[1];
        Key minKey = keys[minIndex];
        // n-- 必须在sink和swim之前做完
        exch(1, n--);
        sink(1);
        qp[minIndex] = -1;
        keys[minIndex] = null; // fire gc
        pq[n + 1] = -1; // 冗余
        return minKey;
    }

    @Override
    public Key keyOf(int i) {
        if (i < 0 || i>= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new IllegalArgumentException("index is not in the priority queue");

        return keys[i];
    }

    @Override
    public void changeKey(int i, Key key) {
        if (i < 0 || i>= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new IllegalArgumentException("index is not in the priority queue");

        int heapIndex = qp[i];
        keys[i] = key;
        sink(heapIndex);
        swim(heapIndex);
    }

    @Override
    public void delete(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");

        int heapIndex = qp[i];
        // 交换之后尽快把总数减掉，以防上浮或下沉操作时，删掉的元素还在参与计算
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

    private class HeapIterator implements Iterator<Entry> {

        private IndexMinPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPQ<>(pq.length - 1);
            // 只复制pq即可
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        /**
         * 这个API的设计有优化的可能，应该是next()直接返回下一个Key的
         * @return 返回下一个元素的关联索引
         */
        @Override
        public Entry next() {
            if (!hasNext()) throw new NoSuchElementException();
            return new Entry(copy.minIndex(), copy.delMin());
        }
    }


    // test suite

    /**
     * Unit tests the {@code IndexMinPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
//        for (int i = 0; i < strings.length; i++) {
//            pq.insert(i, strings[i]);
//        }
//
//        // delete and print each key
//        while (!pq.isEmpty()) {
//            int index = pq.minIndex();
//            String key = pq.delMin();
//            StdOut.println(index + " " + key);
//        }
//        StdOut.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        pq.changeKey(6, "awesome");

        pq.delete(8);

        // print each key using the iterator
        for (Entry e : pq) {
//            StdOut.println(i);
            StdOut.println(e.getIndex() + " " + e.getKey());
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }

    }
}