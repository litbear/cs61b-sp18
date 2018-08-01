package disc03;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SLListTest {

    @Test
    public void testToString() {
        SLList list = SLList.of(1, 2, 3, 4, 5);
        assertEquals("(1, 2, 3, 4, 5)", list.toString());
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
        assertEquals("(5, 10, 6, 2)", l1.toString());
        l1.insert(99, 3000);
        assertEquals("(5, 10, 6, 2, 99)", l1.toString());
    }

    @Test
    public void testReverseIter() {
        SLList oneItemList = SLList.of(1);
        oneItemList.reverseIter();
        assertEquals("(1)", oneItemList.toString());
        SLList list = SLList.of(1, 2, 3, 4, 5);
        assertEquals("(1, 2, 3, 4, 5)", list.toString());
        list.reverseIter();
        assertEquals("(5, 4, 3, 2, 1)", list.toString());
    }

    @Test
    public void testReverse() {
        SLList oneItemList = SLList.of(9527);
        oneItemList.reverse();
        assertEquals("(9527)", oneItemList.toString());
        SLList twoItemList = SLList.of(9527, 9528);
        twoItemList.reverse();
        assertEquals("(9528, 9527)", twoItemList.toString());
        SLList list = SLList.of(1, 2, 3, 4, 5);
        assertEquals("(1, 2, 3, 4, 5)", list.toString());
        list.reverse();
        assertEquals("(5, 4, 3, 2, 1)", list.toString());
    }
}
