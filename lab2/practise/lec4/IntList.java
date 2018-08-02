package lec4;

import org.w3c.dom.Node;

import java.util.Formatter;

/**
 *
 */
@SuppressWarnings({"WeakerAccess", "Duplicates"})
public class IntList {

    public int first;

    public IntList rest;


    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }


    /**
     * problem 5 from midterm 1 in Kartik’s textbook
     * 原题目
     *
     * We want to add a method to IntList so that if 2 numbers in a row are the
     * same, we add them together and make one large node. For example:
     * 1 ⇒ 1 ⇒ 2 ==>3 becomes 2 ⇒ 2 ⇒ 3 which becomes 4 ⇒ 3.
     * For this problem,
     * you will not have access to any add, size, or remove method
     *
     * 题目含混不清，答案有问题...特别二 这题不做了
     */
    public void addAdjacentOnce() {

    }


    /**
     *
     * Modify the Intlist class so that every time you add a value you “square” the IntList.
     * For example, upon the insertion of 5, the below IntList would transform from:
     *
     *    1 => 2 to
     *
     *    1 => 1 => 2 => 4 => 5
     *
     * and if 7 was added to the latter IntList, it would become
     *
     *    1 => 1 => 1 => 1 => 2 => 4 => 4 => 16 => 5 => 25 => 7
     *
     * Additionally, you are provided the constraint that you can only access the
     * size() function one time during the entire process of adding a node.
     *
     */
    public static void squareAndAdd(IntList l, int tail) {
        if (l.rest == null) {
            l.rest = new IntList(l.first * l.first, new IntList(tail, null));
            return;
        }
        squareAndAdd(l.rest, tail);
        l.rest = new IntList(l.first * l.first, l.rest);
    }

     /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }

}

