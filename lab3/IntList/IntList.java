import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 *         [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    public int first;
    /**
     * Remaining elements of list.
     */
    public IntList rest;

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
    /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }

    /**
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {

        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */


    /**
     * Returns a list consisting of the elements of A followed by the
     * *  elements of B.  May modify items of A. Don't use 'new'.
     */

    public static IntList dcatenate(IntList A, IntList B) {
        IntList c;
        c = A;
        while (c.rest != null) {
            c = c.rest;
        }
        c.rest = B;
        return A;
    }

    /**
     * 复制一个每个值完全相同的链表
     * @param src 源链表
     * @return 复制后的链表
     */
    public static IntList copy(IntList src) {
        if (src == null) {
            return null;
        }
        if (src.rest == null) {
            return new IntList(src.first, null);
        }
        return new IntList(src.first, IntList.copy(src.rest));
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     */
    public static IntList catenate(IntList A, IntList B) {
        IntList cloneA = IntList.copy(A);
        return IntList.dcatenate(cloneA, B);
    }

    public static IntList reverse(IntList node) {
        if (node == null || node.rest == null) {
            return node;
        }
        IntList newHead = IntList.reverse(node.rest);
        node.rest.rest = node;
        node.rest = null;
        return newHead;
    }


    /**
     * 返回链表的指定元素
     * @param index 索引
     * @return 指定元素
     */
    public int get(int index) {
        if (index == 0) {
            return first;
        }
        return this.rest.get(index - 1);
    }


    /**
     * 获取当前链表长度 递归形式
     * @return 链表长度
     */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + rest.size();
    }


    /**
     * 获取当前链表长度 迭代形式
     * @return 链表长度
     */
    public int iterativeSize() {
        IntList c = this;
        int size = 1;
        while (c.rest != null) {
            size += 1;
            c = c.rest;
        }
        return size;
    }


    /**
     * 新建链表，其每个元素比原链表增加 x
     * @param L 原链表
     * @param x 增加值
     * @return 结果链表
     */
    public static IntList incrList(IntList L, int x) {
        if (L.rest == null) {
            return new IntList(L.first + x, null);
        }
        return new IntList(L.first + x, IntList.incrList(L.rest, x));
    }


    /**
     * 操作链表，使其每个元素都减少 x
     * @param L 待操作链表
     * @param x 减少值
     * @return 操作结果
     */
    public static IntList dincrList(IntList L, int x) {
        L.first -= x;
        if (L.rest == null) {
            return L;
        }
        return IntList.dincrList(L.rest, x);
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

