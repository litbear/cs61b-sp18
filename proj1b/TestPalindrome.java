import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testOneLetterWordAllPalindrome() {
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("b"));
        assertTrue(palindrome.isPalindrome("c"));
        assertTrue(palindrome.isPalindrome("d"));
        assertTrue(palindrome.isPalindrome("e"));
        assertTrue(palindrome.isPalindrome("f"));
        assertTrue(palindrome.isPalindrome("g"));
    }

    @Test
    public void testWordIsPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("eye"));
        assertTrue(palindrome.isPalindrome("bob"));
        assertTrue(palindrome.isPalindrome("eve"));
        assertTrue(palindrome.isPalindrome("aya"));

        assertFalse(palindrome.isPalindrome("abc"));
        assertFalse(palindrome.isPalindrome("bbc"));
        assertFalse(palindrome.isPalindrome("cbb"));
        assertFalse(palindrome.isPalindrome("cnbc"));
        assertFalse(palindrome.isPalindrome("nyt"));
        assertFalse(palindrome.isPalindrome("bloomberg"));
    }
}
