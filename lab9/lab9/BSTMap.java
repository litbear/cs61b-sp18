package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * 逐步开发共分以下步骤，并非按序实现
 * 1. 递归方式 添加，查找
 * 2. 递归方式 删除 void 以及  返回值为被删除元素
 * 3. 包装对象实现 size
 * 4. 递归实现 size
 * 5. 合法性检验
 * 6. 迭代方式实现 添加 查找 删除
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) return null;

        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        } else {
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) return new Node(key, value);

        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        // putHelper 返回Node getHelper 和 get 却都返回V，
        // 这样在允许插入null值的情况下无法判断是找到了一个null值还是根本不存在对应的键
        if (key == null || value == null) throw new IllegalArgumentException("calls put() with a null key or value");
        root = putHelper(key, value, root);
        size += 1;
        assert check();
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySetHelper(root, set);
        return set;
    }

    private void keySetHelper(Node node, Set<K> set) {
        if (node == null) return;
        set.add(node.key);
        keySetHelper(node.left, set);
        keySetHelper(node.right, set);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V removed = get(key);
        if (removed != null) {
            root = remove(root, key);
            size -= 1;
            assert check();
        }
        return removed;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (value == null) throw new IllegalArgumentException("calls remove() with a null value");
        V removed = get(key);
        if (value.equals(removed)) {
            root = remove(root, key);
            size -= 1;
            assert check();
        }
        return null;
    }

    /**
     * 这个要重点理解 很晦涩
     *
     * @param node 待操作二叉树节点
     * @return 去除最大元素后的二叉树
     */
    private Node removeMax(Node node) {
        if (node.right == null) return node.left;
        node.right = removeMax(node.right);
        return node;
    }
    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node temp = node;
            node = max(node.left);
            node.left = removeMax(temp.left);
            node.right = temp.right;
        }
        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K>{

        Stack<Node> nodeStack = new Stack<>();

        BSTIterator() {
            push(root);
        }

        private void push(Node node){
            while (node != null) {
                nodeStack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        @Override
        public K next() {
            Node node = nodeStack.pop();
            push(node.right);
            return node.key;
        }
    }

    public void print() {
        print(root, 0);
    }

    private void print(Node node, int i) {
        if (node == null){
            return;
        }
        print(node.left, i + 1);
        String indent = IntStream.range(0, i).mapToObj(e -> "-").collect(Collectors.joining(""));
        System.out.println(indent + ">" + node.key);
        print(node.right, i + 1);
    }

    //-----------------------数据结构合法性检验------------------------------

    private boolean check() {
        return
        // 概念检验
        isBST();
        // size 检验
        // rank 检验
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node node, K min, K max) {
        if (node == null) return true;
        if (min != null && node.key.compareTo(min) <= 0) return false;
        if (max != null && node.key.compareTo(max) >= 0) return false;
        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }


}
