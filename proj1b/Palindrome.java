import java.util.Arrays;

public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        if (word == null ){
            return null;
        }
        ArrayDeque<Character> arrayDeque = new ArrayDeque<>();
        word.chars().mapToObj(e -> (char) e).forEach(arrayDeque::addLast);
        return arrayDeque;
    }

    public boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }
        ArrayDeque arrayDeque = (ArrayDeque) wordToDeque(word);
        return isPalindrome(arrayDeque);
    }

    private boolean isPalindrome(ArrayDeque arrayDeque) {
        if (arrayDeque.size() < 2) {
            return true;
        }
        Character head = (Character) arrayDeque.removeFirst();
        Character tail = (Character) arrayDeque.removeLast();
        return (head != null && head.equals(tail) && isPalindrome(arrayDeque));
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() < 2) {
            return true;
        }
        ArrayDeque arrayDeque = (ArrayDeque) wordToDeque(word);
        return isPalindrome(arrayDeque, cc);
    }

    private boolean isPalindrome(ArrayDeque arrayDeque, CharacterComparator cc) {
        if (arrayDeque.size() < 2) {
            return true;
        }
        Character head = (Character) arrayDeque.removeFirst();
        Character tail = (Character) arrayDeque.removeLast();
        return (head != null && tail !=null && cc.equalChars(head, tail) && isPalindrome(arrayDeque, cc));
    }
}
