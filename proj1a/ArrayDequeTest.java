import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArrayDequeTest {

    private final static class Man{
        String name;
        int age;

        public Man(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Man<" + name + ", " + age + ">";
        }
    }

    @Test
    public void testAddFirst() {
        ArrayDeque<Man> arrayDeque = new ArrayDeque<>(20);
        arrayDeque.addFirst(new Man("Ann", 1));
        arrayDeque.addFirst(new Man("Bob", 2));
        arrayDeque.addFirst(new Man("Candy", 3));
        arrayDeque.addFirst(new Man("Donald", 4));
        arrayDeque.addFirst(new Man("Ella", 5));
        System.out.println(arrayDeque);
    }

    @Test
    public void testRemoveFirstNull() {
        ArrayDeque<Man> arrayDeque = new ArrayDeque<>(10);
        Man m = arrayDeque.removeFirst();
        assertNull(m);
        m = arrayDeque.removeFirst();
        assertNull(m);
        m = arrayDeque.removeFirst();
        assertNull(m);
        System.out.println(arrayDeque);
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Man> arrayDeque = new ArrayDeque<>(10);
        Man ann = new Man("Ann", 1);
        Man bob = new Man("Bob", 2);
        Man candy = new Man("Candy", 3);
        Man donald = new Man("Donald", 4);
        Man ella = new Man("Ella", 5);
        arrayDeque.addFirst(ann);
        arrayDeque.addFirst(bob);
        arrayDeque.addFirst(candy);
        arrayDeque.addFirst(donald);
        arrayDeque.addFirst(ella);
        Man m = arrayDeque.removeFirst();
        assertEquals(ella, m);
        m = arrayDeque.removeFirst();
        assertEquals(donald, m);
        m = arrayDeque.removeFirst();
        assertEquals(candy, m);
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        System.out.println(arrayDeque);
    }

    @Test
    public void testGetFirst() {
        ArrayDeque<Man> arrayDeque = new ArrayDeque<>(10);
        Man ann = new Man("Ann", 1);
        Man bob = new Man("Bob", 2);
        Man candy = new Man("Candy", 3);
        Man donald = new Man("Donald", 4);
        Man ella = new Man("Ella", 5);
        arrayDeque.addFirst(ann);
        arrayDeque.addFirst(bob);
        arrayDeque.addFirst(candy);
        arrayDeque.addFirst(donald);
        arrayDeque.addFirst(ella);
        arrayDeque.removeFirst();
        Man m = arrayDeque.getFirst();
        assertEquals(donald, m);
        arrayDeque.removeFirst();
        m = arrayDeque.getFirst();
        assertEquals(candy, m);
        m = arrayDeque.removeFirst();
        assertEquals(candy, m);
        System.out.println(arrayDeque);
    }

    @Test
    public void testAddLast() {
        ArrayDeque<Man> arrayDeque = new ArrayDeque<>(10);
        Man ann = new Man("Ann", 1);
        Man bob = new Man("Bob", 2);
        Man candy = new Man("Candy", 3);
        Man donald = new Man("Donald", 4);
        Man ella = new Man("Ella", 5);
        Man frank = new Man("frank", 6);
        arrayDeque.addLast(candy);
        arrayDeque.addLast(donald);
        arrayDeque.addFirst(bob);
        arrayDeque.addLast(ella);
        arrayDeque.addFirst(ann);
        arrayDeque.addLast(frank);
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        System.out.println(arrayDeque);
    }

    @Test
    public void testRemoveLastNull() {
        ArrayDeque<Man> arrayDeque = new ArrayDeque<>(10);
        Man m = arrayDeque.removeLast();
        assertNull(m);
        m = arrayDeque.removeLast();
        assertNull(m);
        m = arrayDeque.removeLast();
        assertNull(m);
        System.out.println(arrayDeque);
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Man> arrayDeque = new ArrayDeque<>(10);
        Man ann = new Man("Ann", 1);
        Man bob = new Man("Bob", 2);
        Man candy = new Man("Candy", 3);
        Man donald = new Man("Donald", 4);
        Man ella = new Man("Ella", 5);
        arrayDeque.addLast(ann);
        arrayDeque.addLast(bob);
        arrayDeque.addLast(candy);
        arrayDeque.addLast(donald);
        arrayDeque.addLast(ella);
        Man m = arrayDeque.removeLast();
        assertEquals(ella, m);
        m = arrayDeque.removeLast();
        assertEquals(donald, m);
        m = arrayDeque.removeLast();
        assertEquals(candy, m);
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        System.out.println(arrayDeque);
    }

    @Test
    public void testGetLast() {
        ArrayDeque<Man> arrayDeque = new ArrayDeque<>(10);
        Man ann = new Man("Ann", 1);
        Man bob = new Man("Bob", 2);
        Man candy = new Man("Candy", 3);
        Man donald = new Man("Donald", 4);
        Man ella = new Man("Ella", 5);
        arrayDeque.addLast(ann);
        arrayDeque.addLast(bob);
        arrayDeque.addLast(candy);
        arrayDeque.addLast(donald);
        arrayDeque.addLast(ella);
        System.out.println(arrayDeque);
        arrayDeque.removeLast();
        System.out.println(arrayDeque);
        Man m = arrayDeque.getLast();
        assertEquals(donald, m);
        arrayDeque.removeFirst();
        System.out.println(arrayDeque);
        m = arrayDeque.getLast();
        assertEquals(donald, m);
        m = arrayDeque.removeLast();
        assertEquals(donald, m);
        System.out.println(arrayDeque);
    }

    @Test
    public void  testGetIndex() {
        ArrayDeque<Man> arrayDeque = new ArrayDeque<>(10);
        Man ann = new Man("Ann", 1);
        Man bob = new Man("Bob", 2);
        Man candy = new Man("Candy", 3);
        Man donald = new Man("Donald", 4);
        Man ella = new Man("Ella", 5);
        arrayDeque.addLast(candy);
        arrayDeque.addFirst(bob);
        arrayDeque.addFirst(ann);
        arrayDeque.addLast(donald);
        arrayDeque.addLast(ella);

        Man m = arrayDeque.get(8);
        assertNull(m);

        m = arrayDeque.get(0);
        assertEquals(m, ann);
        m = arrayDeque.get(1);
        assertEquals(m, bob);
        m = arrayDeque.get(2);
        assertEquals(m, candy);
        m = arrayDeque.get(3);
        assertEquals(m, donald);
        m = arrayDeque.get(4);
        assertEquals(m, ella);

        System.out.println(arrayDeque);
    }
}
