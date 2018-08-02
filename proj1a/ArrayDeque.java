import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayDeque<T> {

    public static final int DEFAULT_ARRAY_SIZE = 8;

    private T[] items;
    private int size;
    private int pointer = 0;

    public ArrayDeque(int initSize) {
        items = (T[]) new Object[initSize];
        size = 0;
    }

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_ARRAY_SIZE];
        size = 0;
    }

    private boolean isAlmostFull(){
        return true;
    }

    private boolean isAlmostEmpty() {
        return true;
    }

    private void resize() {

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

    public int size() {
        return 0;
    }

    // @Todo
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
