package lec4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("Duplicates")
public class IntListTest {

    @Test
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.of(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    @Test
    public void testAddAdjacentOnce() {

    }

    @Test
    public void testAddAndSquare() {

        IntList l0 = IntList.of(1);
        IntList.squareAndAdd(l0, 5);
        assertEquals("(1, 1, 5)", l0.toString());

        IntList l1 = IntList.of(1, 2);
        IntList.squareAndAdd(l1, 5);
        assertEquals("(1, 1, 2, 4, 5)", l1.toString());

        IntList l2 = IntList.of(3, 2, 1);
        IntList.squareAndAdd(l2, 5);
        assertEquals("(3, 9, 2, 4, 1, 1, 5)", l2.toString());

        IntList l3 = IntList.of(1, 2);
        IntList.squareAndAdd(l3, 5);
        assertEquals("(1, 1, 2, 4, 5)", l3.toString());
        IntList.squareAndAdd(l3, 7);
        assertEquals("(1, 1, 1, 1, 2, 4, 4, 16, 5, 25, 7)", l3.toString());
    }
}
