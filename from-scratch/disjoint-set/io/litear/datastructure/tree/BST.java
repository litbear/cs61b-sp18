package io.litear.datastructure.tree;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public BST() {
    }

    // 获取

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (null == key) {
            throw new IllegalArgumentException("calls get() with a null key");
        }

        if (null == node) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if(cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public boolean isContain(Key key) {
        if (key == null) throw new IllegalArgumentException("calls contains() with a null key");
        return get(key) != null;
    }

    // 添加

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (null == node) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    // 最大值与最小值
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

    // 删除
    public void deleteMin(){
        if (root == null) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    public void deleteMax(){
        if (root == null) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }


    private Node delete(Node node, Key key){
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // 向左查找
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            // 向右查找
            node.right = delete(node.right, key);
        } else {
            // 0 或 1 个子节点的情况下，直接用子节点代替当前节点即可
            if (node.right == null) return node.left;
            if (node.left  == null) return node.right;
            // 有两个子节点的情况下开始操作
            // 缓存当前节点
            Node temp = node;
            // 获取当前节点左子树最大值作为新的节点
            node = max(temp.left);
            // 将缓存节点的删除后的左子树作为新节点的左子树
            node.left = deleteMax(temp.left);
            // 将缓存节点的右子树作为新节点的右子树
            node.right = temp.right;
        }
        return node;
    }
}
