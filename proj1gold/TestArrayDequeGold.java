import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    static StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
    static ArrayDequeSolution ads = new ArrayDequeSolution();

    @Test
    public void testStudentArrayDeque() {
        for (int i = 0; i < 1000; i += 1) {
            double random = StdRandom.uniform();

            if (random < 0.25) {
                System.out.println("addFirst(" + i + ")");
                ads.addFirst(i);
                sad.addFirst(i);
                assertEquals(
                        String.format("addFirst(%d)", i),
                        ads.get(0),
                        sad.get(0)
                );
            } else if(random >= 0.25 && random < 0.5) {
                System.out.println("addLast(" + i + ")");
                ads.addLast(i);
                sad.addLast(i);
                assertEquals(
                        String.format("addLast(%d)", i),
                        ads.get(ads.size() - 1),
                        sad.get(sad.size() - 1)
                );
            } else if(random >= 0.5 && random < 0.75) {
                if (ads.size() == 0 || sad.size() == 0) {
                    continue;
                }
                System.out.println("removeLast()");
                assertEquals(
                        "removeLast()",
                        ads.removeLast(),
                        sad.removeLast()
                );
            } else {
                if (ads.size() == 0 || sad.size() == 0) {
                    continue;
                }
                System.out.println("removeFirst()");
                assertEquals(
                        "removeFirst()",
                        ads.removeFirst(),
                        sad.removeFirst()
                );
            }
        }
    }
}
