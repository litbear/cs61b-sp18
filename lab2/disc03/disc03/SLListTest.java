package disc03;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SLListTest {

    @Test
    public void testReverse() {
    }

    @Test
    public void testToString() {
        SLList list = SLList.of(1, 2, 3, 4, 5);
        assertEquals(list.toString(), "(1, 2, 3, 4, 5)");
    }

    @Test
    public void testEquals() {
        SLList l1 = SLList.of(1, 2, 3, 4, 5);
        SLList l2 = SLList.of(1, 2, 3, 4, 5);
        assertNotEquals(l1, l2);
    }

    @Test
    public void testInsert() {
        SLList l1 = SLList.of(5, 6, 2);
        l1.insert(10, 1);
        assertEquals(l1.toString(), "(5, 10, 6, 2)");
        l1.insert(99, 3000);
        assertEquals(l1.toString(), "(5, 10, 6, 2, 99)");
    }

    @Test
    public void testReverseIter() {
        SLList oneItemList = SLList.of(1);
        oneItemList.reverseIter();
        assertEquals(oneItemList.toString(), "(1)");
        SLList list = SLList.of(1, 2, 3, 4, 5);
        assertEquals(list.toString(), "(1, 2, 3, 4, 5)");
        list.reverseIter();
        assertEquals(list.toString(), "(5, 4, 3, 2, 1)");
    }
}
