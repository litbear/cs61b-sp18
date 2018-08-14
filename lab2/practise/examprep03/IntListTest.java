package examprep03;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IntListTest {

    @Test
    public void testToString() {
        IntList l = new IntList(1, null);
        IntList p = l;
        p.rest = new IntList(2, null);
        p = p.rest;
        p.rest = new IntList(3, null);
        assertEquals("1, 2, 3", l.toString());
    }

    @Test
    public void testListIter() {
        IntList l = IntList.listIter(1, 2, 3, 4, 5);
        assertEquals("1, 2, 3, 4, 5", l.toString());
    }

    @Test
    public void testList() {
        IntList l = IntList.list(1, 2, 3, 4, 5);
        assertEquals("1, 2, 3, 4, 5", l.toString());
    }

    @Test
    public void testSkippifyIter() {
        IntList l = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        l.skippifyIter();
        assertEquals("1, 3, 6, 10", l.toString());
        IntList l2 = IntList.list(9, 8, 7, 6, 5, 4, 3, 2, 1);
        l2.skippifyIter();
        assertEquals("9, 7, 4", l2.toString());
    }

    @Test
    public void testSkippify() {
        IntList l = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        l.skippify();
        assertEquals("1, 3, 6, 10", l.toString());
        IntList l2 = IntList.list(9, 8, 7, 6, 5, 4, 3, 2, 1);
        l2.skippify();
        assertEquals("9, 7, 4", l2.toString());
    }

    @Test
    public void testRemoveDuplicatesIter() {
        IntList l = IntList.list(1, 2, 2, 3);
        IntList.removeDuplicatesIter(l);
        assertEquals("1, 2, 3", l.toString());
        IntList l2 = IntList.list(1, 2, 2, 3, 3, 3, 4, 4, 5);
        IntList.removeDuplicatesIter(l2);
        assertEquals("1, 2, 3, 4, 5", l2.toString());
    }

    @Test
    public void testRemoveDuplicates() {
        IntList l = IntList.list(1, 2, 2, 3);
        IntList.removeDuplicates(l);
        assertEquals("1, 2, 3", l.toString());
        IntList l2 = IntList.list(1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5);
        IntList.removeDuplicates(l2);
        assertEquals("1, 2, 3, 4, 5", l2.toString());
    }
}
