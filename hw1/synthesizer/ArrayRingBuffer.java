package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //       Create new array with capacity elements.
        rb = (T[]) new Object[capacity];
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
    }

    private static int inc(int index, int length) {
        index += 1;
        if (index == length) {
            return 0;
        }
        return index;
    }

    private static int sub(int tail, int head, int length) {
        int size = tail - head;
        return size < 0 ? (size + length) : size;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = inc(last, capacity());
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer empty");
        }
        // Dequeue the first item. Don't forget to decrease fillCount and update
        T result = rb[first];
        rb[first] = null;
        first = inc(first, capacity());
        fillCount -= 1;
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer Underflow");
        }
        return rb[first];
    }

    private class ArrayRingBufferIterator implements Iterator<T> {

        private int point = first;
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count != fillCount();
        }

        @Override
        public T next() {
            T result = rb[point];
            point = inc(point, capacity());
            count += 1;
            return result;
        }
    }

    private class ArrayRingBufferMutableIterator implements Iterator<T> {


        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public T next() {
            return dequeue();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferMutableIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

}
