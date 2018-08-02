import java.util.Arrays;
import java.util.stream.Collectors;

public class AList<T> {

    public static final int DEFALUT_ARRAY_SIZE = 8;

    private T[] items;
    private int size;

    public AList() {
        items = (T[]) new Object[DEFALUT_ARRAY_SIZE];
        size = 0;
    }

    public AList(int initSize) {
        items = (T[]) new Object[initSize];
        size = 0;
    }

    private boolean isAlmostFull(){
        return size > (items.length >> 1);
    }

    private boolean isAlmostEmpty() {
        return size < (items.length >> 2);
    }

    private void resize() {
        @SuppressWarnings("unchecked") T[] newItems = (T[]) new Object[items.length << 1];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    private void shrink() {
        @SuppressWarnings("unchecked") T[] newItems = (T[]) new Object[items.length >> 1];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    public void addLast(T item) {
        if (isAlmostFull()) {
            resize();
        }
        items[size] = item;
        size += 1;
    }

    public T getLast() {
        return items[size-1];
    }

    @SuppressWarnings("Duplicates")
    public T removeLast() {
        T last = getLast();
        items[size - 1] = null;
        size -= 1;
        if (isAlmostEmpty()) {
            shrink();
        }
        return last;
    }

    public T get(int i) {
        return items[i];
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String content = Arrays
                .stream(Arrays.copyOfRange(items, 0, size))
                .map(T::toString)
                .collect(Collectors.joining(", "));
        return getClass().getName() + "<" + content + ">";
    }
}
