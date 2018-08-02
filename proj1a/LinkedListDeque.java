import java.util.Objects;

public class LinkedListDeque<T> {

    private final static class Node<T> {
        Node prev;
        T item;
        Node next;

        Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        Node() {
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<T>();
        sentinel.prev = sentinel;
        sentinel.item = null;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        // 缓存原链表头
        Node originalHead = sentinel.next;
        // 新节点前驱 指向 原链表头前驱
        // 新节点后继 指向 原链表头
        Node<T> node = new Node<>(originalHead.prev, item, originalHead);
        // 原链表头前驱 指向新节点
        originalHead.prev = node;
        // 守卫节点后继 指向新节点
        sentinel.next = node;

        size += 1;
    }

    public void addLast(T item) {
        // 缓存原链表尾
        Node originalTail = sentinel.prev;
        // 新节点前驱 指向原链表尾
        // 新节点后继 指向原链表尾后继
        Node<T> node = new Node<>(originalTail, item, originalTail.next);
        // 原链表尾后继 指向新节点
        originalTail.next = node;
        // 守卫节点前驱 指向新节点
        sentinel.prev = node;

        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node pointer = sentinel.next;
        while (pointer != sentinel) {
            System.out.print(pointer.item + ", ");
            pointer = pointer.next;
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
//        if (sentinel.next == sentinel) {
//            return null;
//        }
        // 取得 链表头
        Node<T> head = sentinel.next;
        // 取得 新链表头
        Node newHead = head.next;
        // 游离 Node
        head.prev = null;
        head.next = null;
        // 新链表头前驱 指向 守卫节点
        newHead.prev = sentinel;
        // 守卫节点后继 指向 新链表头
        sentinel.next = newHead;

        size -= 1;
        return head.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        // 取得 链表尾
        Node<T> tail = sentinel.prev;
        // 取得 新链表尾
        Node newTail = tail.prev;
        // 游离 Node
        tail.prev = null;
        tail.next = null;
        // 新链表尾后继 指向 守卫节点
        newTail.next = sentinel;
        // 守卫节点前驱 指向 新链表尾
        sentinel.prev = newTail;

        size -= 1;
        return tail.item;
    }

    /**
     * 获取指定位置的元素
     *
     * 看了 LinkedList 的实现，会和 size 的一半
     * 相比较 然后决定是从头查还是从尾查
     *
     * @param index
     * @return
     */
    public T get(int index) {
//        Objects.checkIndex(index, size);
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> pointer = sentinel.next;
        while (index > 0) {
            pointer = pointer.next;
            index -= 1;
        }
        return pointer.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> resultNode = recursiveGetNode(sentinel.next, index);
        return resultNode.item;
    }

    private Node recursiveGetNode(Node node, int index) {
        if (index == 0) {
            return node;
        }
        return recursiveGetNode(node.next, index - 1);
    }

}
