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
    private Node root;
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
        return node;
    }
}
