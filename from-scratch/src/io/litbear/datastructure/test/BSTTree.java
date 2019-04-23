package io.litbear.datastructure.test;

import io.litbear.datastructure.tree.BST;
import org.junit.Test;

public class BSTTree {

    @Test
    public void testPut() {
        char[] chars = new char[] {'h', 'c', 's', 'a', 'e', 'r', 'x', 'b'};

        BST<Character, Character> bst = new BST<>();

        for (char c: chars) {
            bst.put(c, c);
        }

        System.out.println(bst);
    }

    @Test
    public void testDeleteMin() {
        char[] chars = new char[] {'h', 'c', 's', 'a', 'e', 'r', 'x', 'b'};

        BST<Character, Character> bst = new BST<>();

        for (char c: chars) {
            bst.put(c, c);
        }
        bst.deleteMin();
        System.out.println(bst);
    }

    @Test
    public void testDelete() {
        char[] chars = new char[] {'h', 'c', 's', 'a', 'e', 'r', 'x', 'b'};

        BST<Character, Character> bst = new BST<>();

        for (char c: chars) {
            bst.put(c, c);
        }
        bst.delete('h');
        System.out.println(bst);
    }
}
