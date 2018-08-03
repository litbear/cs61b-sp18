import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayDeque<T> {

    public static final int DEFAULT_ARRAY_SIZE = 8;

    private T[] items;

    /**
     * the index of element remove() or pop() will operate,
     * HOWEVER addFirst() will add element BEFORE it;
     * there IS an element in this index.
     */
    private int head;

    /**
     * the index of element addLast(E), add(E), or push(E) will operate,
     * there IS NOT element in this index.
     */
    private int tail;

    public ArrayDeque(int initSize) {
        items = (T[]) new Object[initSize];
    }

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    private boolean isAlmostFull() {
        return true;
    }

    private boolean isAlmostEmpty() {
        return true;
    }

    private void grow() {

    }

    private void shrink() {

    }

    public void addFirst(T item) {

    }

    public T getLast() {
        return null;
    }

    public void addLast(T item) {

    }

    public boolean isEmpty() {
        return true;
    }

    private static int inc(int index, int length) {
        index += 1;
        if (index >= length) {
            index = 0;
        }
        return index;
    }

    private static int dec(int index, int length) {
        index -= 1;
        if (index < 0) {
            index += length;
        }
        return index;
    }

    private static int sub(int tail, int head, int length) {
        int size = tail - head;
        return size < 0 ? (size + length) : size;
    }

    public int size() {
        return sub(tail, head, items.length);
    }

    public T getFirst() {
        return null;
    }


    public void printDeque() {
    }

    public T removeFirst() {
        return null;
    }

    @SuppressWarnings("Duplicates")
    public T removeLast() {
        return null;

    }

    public T get(int index) {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
