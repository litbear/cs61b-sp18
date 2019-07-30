package io.litbear.datastructure.tree;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public RedBlackBST() {
    }

    // utils
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    /**
     * 右旋也是涉及两个节点的操作
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        // 这个并不是判定条件，只是进入本函数的前置条件而已
        assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = x.right.color; // 恢复原位置颜色
        h.color = RED; // 等待颜色翻转

        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;

        return x;
    }

    /**
     * 左旋其实只是涉及两个节点的操作，
     * 因此颜色上不必顾及h的父节点，
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color; // 恢复原位置颜色
        h.color = RED;

        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;

        return x;
    }

    private void flipColors(Node h) {
        // h must have opposite color of its two children
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h) && isRed(h.left) && isRed(h.right))
                || (isRed(h) && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    //-------------------------------------------
    // api
    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int compare = key.compareTo(x.key);
            if (compare < 0) x = x.left;
            else if (compare > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (null == value) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (null == node) return new Node(key, value, RED, 1);
        int cmp = node.key.compareTo(key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if(cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;

        if (!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }
}
