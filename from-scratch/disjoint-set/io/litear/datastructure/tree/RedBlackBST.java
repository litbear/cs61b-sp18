package io.litear.datastructure.tree;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    public RedBlackBST() {
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    /**
     * 左旋红黑树
     *
     * @param h 待操作节点
     * @return 操作后的节点
     */
    private Node rotateLeft(Node h) {
        // null 检查根本不用加，因为put方法内已经使用isRed(node.right)为true对h的右节点的非空性进行了检查
        if (h == null) return null;
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    /**
     * 右旋红黑树
     *
     * @param h 待操作节点
     * @return 操作后的节点
     */
    private Node rotateRight(Node h) {
        // null 检查根本不用加，因为put方法内已经使用isRed()为true对h的非空性进行了检查
        if (h == null) return null;
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    /**
     * 反转指定节点与其左右子节点的颜色
     *
     * @param node 待操作节点
     */
    private void flipColors(Node node) {
        // null 检查根本不用加，因为isRed方法已经将null识别为BLACK了
        if (node == null) return;
        node.color = !node.color;
        node.right.color = !node.right.color;
        node.left.color = !node.left.color;
    }

    // ------------------GET----------------------
    public boolean isContain(Key key) {
        if (key == null) throw new IllegalArgumentException("calls contains() with a null key");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    // ------------------PUT---------------------
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, RED);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if(cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        // 首先 if (node == null) return 这一步拦截了所有的null，执行到这一行以下的node都是Node实例
        // 其次 既然node都是Node实例了，那么node.left.left就不会产生NullPointer
        if (!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
    }
}
