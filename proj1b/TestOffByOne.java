import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.


    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'c'));
        assertTrue(offByOne.equalChars('c', 'd'));
        assertTrue(offByOne.equalChars('d', 'e'));
        assertTrue(offByOne.equalChars('e', 'f'));
        assertTrue(offByOne.equalChars('f', 'g'));

        assertTrue(offByOne.equalChars('b', 'a'));
        assertTrue(offByOne.equalChars('c', 'b'));
        assertTrue(offByOne.equalChars('d', 'c'));
        assertTrue(offByOne.equalChars('e', 'd'));
        assertTrue(offByOne.equalChars('f', 'e'));
        assertTrue(offByOne.equalChars('g', 'f'));

        assertFalse(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('b', 'C'));
        assertFalse(offByOne.equalChars('c', 'D'));
        assertFalse(offByOne.equalChars('d', 'E'));
        assertFalse(offByOne.equalChars('e', 'F'));
        assertFalse(offByOne.equalChars('f', 'G'));

        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('z', 'a'));
        assertFalse(offByOne.equalChars('a', 'a'));

    }

}
