package byog.lab5;

import org.junit.Test;

import java.util.Random;

public class RandomTest {

    @Test
    public void testSeed() {
        Random r = new Random(1000);
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
        r = new Random(1000);
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
    }
}
