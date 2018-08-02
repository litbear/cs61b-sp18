import org.junit.Test;

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
    public void testPrint() {
        ArrayDeque arrayDeque = new ArrayDeque(20);
        arrayDeque.addLast(new Man("Ann", 1));
        arrayDeque.addLast(new Man("Bob", 2));
        arrayDeque.addLast(new Man("Candy", 3));
        arrayDeque.addLast(new Man("Donald", 4));
        arrayDeque.addLast(new Man("Ella", 5));
        arrayDeque.printDeque();
    }
}
