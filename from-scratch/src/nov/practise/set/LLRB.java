package nov.practise.set;

/**
 * 11月15日 练习代码 根据 http://www.cs.princeton.edu/~rs/talks/LLRB/LLRB.pdf
 * 进行红黑树插入与删除的学习
 *
 * @param <Key>
 * @param <Value>
 */

@SuppressWarnings("Duplicates")
public class LLRB<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean color;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }
    }

    private boolean isRed(Node node) {
        if (null == node) return false;
        return node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node) {
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        node.color = !node.color;
    }

    public LLRB() {
    }

    public Value search(Key key) {
        if (key == null) throw new IllegalArgumentException("calls search() with a null key");
        Node node = root;
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

    public void insert(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("calls insert() with a null key");
        root = insert(root, key, value);
        root.color = BLACK;
    }

    private Node insert(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
        }

        if (!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
    }
}
