import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayDeque<T> implements Deque<T> {

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


    private static int inc(int index, int length) {
        index += 1;
        if (index >= length) {
            index = 0;
        }
        return index;
    }

    private static int inc(int index, int distance, int length) {
        index += distance;
        if (index >= length) {
            index = index - length;
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

    private boolean isAlmostFull() {
        return size() > (items.length >> 1);
    }

    private boolean isAlmostEmpty() {
        return size() < (items.length >> 2);
    }

    private void grow() {
        T[] newItems = (T[]) new Object[items.length << 1];
        arrayCopy(items, newItems);
        items = newItems;
    }

    private void shrink() {
        T[] newItems = (T[]) new Object[items.length >> 1];
        arrayCopy(items, newItems);
        items = newItems;
    }

    private void arrayCopy(T[] src, T[] dst) {
        int length = size();
        if ((tail - head) < 0) {
            System.arraycopy(src, head, dst, 0, (items.length - head));
            System.arraycopy(src, 0, dst, (items.length - head), tail);
        } else {
            System.arraycopy(src, head, dst, 0, length);
        }
        head = 0;
        tail = length;
    }

    @Override
    public void addFirst(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        head = dec(head, items.length);
        items[head] = item;
        if (isAlmostFull()) {
            grow();
        }
    }

    public T getFirst() {
        return items[head];
    }

    @Override
    public T removeFirst() {
        T item = getFirst();
        // if 条件很重要！ 如果不判断，则会造成first一直前移
        if(item == null) {
            return null;
        }
        items[head] = null;
        head = inc(head, items.length);
        if(isAlmostEmpty()) {
            shrink();
        }
        return item;
    }

    @Override
    public void addLast(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        items[tail] = item;
        tail = inc(tail, items.length);
        if (isAlmostFull()) {
            grow();
        }
    }

    public T getLast() {
        /* 使用 dec 保证在边界条件 初始数组长度设为1 的时候
         *   ，tail 为0，而 tail 的前一个元素不至于成为 -1
         */
        int index = dec(tail, items.length);
        return items[index];
    }

    @Override
    public T removeLast() {
        T item = getLast();
        // if 条件很重要！ 如果不判断会造成 tail 一直后移
        if(item == null) {
            return null;
        }
        tail = dec(tail, items.length);
        items[tail] = null;
        if (isAlmostEmpty()) {
            shrink();
        }
        return item;

    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public int size() {
        return sub(tail, head, items.length);
    }

    @Override
    public void printDeque() {
        System.out.println(this);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size() - 1) {
            return null;
        }
        int i = inc(head, index, items.length);
        return items[i];
    }

    @Override
    public String toString() {
        Stream<T> stream;
        if ((tail - head) < 0) {
            stream = Arrays
                    .stream(Arrays.copyOfRange(items, head, (items.length - 1)));
            stream = Stream.concat(stream, Arrays
                    .stream(Arrays.copyOfRange(items, 0, tail)));
        } else {
            stream = Arrays
                    .stream(Arrays.copyOfRange(items, head, size()));
        }
        String string = stream.map(T::toString).collect(Collectors.joining(", "));
        return getClass().getName() + "<" + string + ">";
    }
}
