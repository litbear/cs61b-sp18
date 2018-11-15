package nov.practise.tree;

import java.util.NoSuchElementException;

/**
 * 这是十一月份的练习实现
 */
public class BST<Key extends Comparable<Key>, Value> {

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;

    public BST() {
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }

        if (null == node) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public boolean isContain(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new NoSuchElementException("calls put() with empty symbol table");
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if(cmp > 0){
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public Key min() {
        if (root == null) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    public Key max() {
        if (root == null) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    public void deleteMin() {
        if (root == null) return;
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }


    public void deleteMax() {
        if (root == null) return;
        root = deleteMax(root);
    }

    /**
     * 删除指定节点的最大值节点
     *
     * @param node 待操作节点
     * @return 操作完成侯的节点
     */
    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMin(node.right);
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (key == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node temp = node;
            node = max(temp.left);
            node.left = deleteMax(node.left);
            node.right = temp.right;
        }
        return node;
    }
}
