import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {

    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testEqualCharsOffByN() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('b', 'g'));
        assertTrue(offByN.equalChars('c', 'h'));
        assertTrue(offByN.equalChars('d', 'i'));
        assertTrue(offByN.equalChars('e', 'j'));
        assertTrue(offByN.equalChars('f', 'k'));


        assertFalse(offByN.equalChars('a', 'F'));
        assertFalse(offByN.equalChars('b', 'G'));
        assertFalse(offByN.equalChars('c', 'H'));
        assertFalse(offByN.equalChars('d', 'I'));
        assertFalse(offByN.equalChars('e', 'J'));
        assertFalse(offByN.equalChars('f', 'K'));

        assertFalse(offByN.equalChars('a', 'e'));
        assertFalse(offByN.equalChars('z', 'a'));
        assertFalse(offByN.equalChars('a', 'a'));

    }
}
