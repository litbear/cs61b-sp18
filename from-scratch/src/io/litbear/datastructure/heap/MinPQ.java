package io.litbear.datastructure.heap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Key> implements Iterable<Key> {
    private Key[] pq;                       // 保存优先队列的数组
    private int n;                          // 当前优先队列容量
    private Comparator<Key> comparator;


    // constructor
    public MinPQ() {
        this(1);
    }

    public MinPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MinPQ(int initCapacity, Comparator comparator) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
        this.comparator = comparator;
    }

    public MinPQ(Comparator comparator) {
        this(1, comparator);
    }

    // API

    public void insert(Key v) {
        // resize
        if (n == pq.length - 1) resize(pq.length << 1);

        pq[++n] = v;
        swim(n);
        // assertion
        assert isMinHeap();
    }

    public Key min() {
        return pq[1];
    }

    @SuppressWarnings("Duplicates")
    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");

        Key min = pq[1];
        // 交换之后尽快把总数减掉，以防上浮或下沉操作时，删掉的元素还在参与计算
        exch(1, n--);
        sink(1);

        // fire gc
        pq[n + 1] = null;
        // resize
        if (n > 0 && n < (pq.length - 1) / 4) resize(pq.length >> 1);
        // assertion
        assert isMinHeap();

        return min;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // util
    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j + 1, j)) j++;
            if (less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        return comparator.compare(pq[i], pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    private void resize(int capacity) {
        assert capacity > n;

        Key[] temp = (Key[]) new Object[capacity];
//        for (int i = 1; i <= n; i++)
//            temp[i] = pq[i];
        System.arraycopy(pq, 1, temp, 1, n);

        pq = temp;

    }

    // assertion
    public boolean isMinHeap() {
        return isMinHeap(1);
    }

    @SuppressWarnings("Duplicates")
    private boolean isMinHeap(int k) {
        if (k > n) return true;

        int left = 2 * n, right = 2 * n + 1;
        if (left <= n && less(left, k)) return false;
        if (right <= n && less(right, k)) return false;

        return isMinHeap(2 * k) && isMinHeap(2 * k + 1);
    }

    public class HeapIterator implements Iterator<Key> {

        MinPQ<Key> copy;

        HeapIterator() {
            if (comparator == null) copy = new MinPQ<>(size());
            else                    copy = new MinPQ<>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }


    // Iterator
    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }
}
