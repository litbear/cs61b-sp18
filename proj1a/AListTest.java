import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AListTest {

    @Test
    public void testBitOperators() {
        int a = 8;
        System.out.println(a >> 1);
        System.out.println(a);
        int b = 0;
        System.out.println(b >> 1);
        System.out.println(b);
    }

    @Test
    public void testJoin(){
        String s = String.join(", ", new String[]{"hello", "world"});
        System.out.println(s);
    }

    @Test
    public void testAddLast() {
        AList<Integer> aList = new AList<>();
        aList.addLast(1);
        aList.addLast(2);
        aList.addLast(3);
        aList.addLast(4);
        aList.addLast(5);
    }

}
