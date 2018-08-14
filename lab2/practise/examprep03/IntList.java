package examprep03;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class IntList {
    public int first;
    public IntList rest;

    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    public static IntList listIter(int ...args){
        if (args.length == 0) {
            return null;
        }
        IntList list = new IntList(args[0], null);
        IntList p = list;
        for (int i = 1; i < args.length; i += 1) {
            p.rest = new IntList(args[i], null);
            p = p.rest;
        }
        return list;
    }

    /**
     * 递归方法求解 list
     * @param args
     * @return
     */
    public static IntList list(int ...args) {
        if (args.length == 0) {
            return null;
        }
        if (args.length == 1) {
            return new IntList(args[0], null);
        }
        return new IntList(args[0], IntList.list(Arrays.copyOfRange(args, 1, args.length)));
    }

    @Override
    public String toString() {
        if (rest == null) return String.valueOf(first);
        return String.valueOf(first) + ", " + rest.toString();
    }

    public void skippifyIter() {
        IntList p = this;
        int m = 1;
        while (p != null) {
            IntList next = p.rest;

            for (int i = 0; i < m; i += 1) {
                if (next == null) {
                    break;
                }
                next = next.rest;
            }
            p.rest = next;
            p = p.rest;
            m += 1;
        }
    }

    /**
     * 递归方法求解 skippify
     */
    public void skippify() {
        skippifyHelper(this, 1);
    }

    public static void skippifyHelper(IntList list, int step) {
        if (list == null) return;
        IntList p = list.rest;
        for(int i = step; i > 0; i -= 1) {
            if (p == null) break;
            p = p.rest;
        }
        list.rest = p;
        skippifyHelper(p, step + 1);
    }

    public static void removeDuplicatesIter(IntList p) {
        if (p == null) {
            return;
        }

        IntList current = p.rest;
        IntList previous = p;
        while (current != null) {
            if (current.first == previous.first) {
                previous.rest = current.rest;
            } else {
                previous = current;
            }
            current = current.rest;

        }
    }

    public static void removeDuplicates(IntList p) {
        if (p == null || p.rest == null) {
            return;
        }
        IntList current = p;
        IntList next = p.rest;
        if (current.first == next.first) {
            current.rest = next.rest;
        } else {
            current = current.rest;
        }
        removeDuplicates(current);
    }
}
