package disc03;

import java.util.Formatter;

/**
 * 这是 disc03 的练习代码
 */
public class SLList {
    private static class IntNode {
        int item;
        IntNode next;

        IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(item);
        }
    }

    private IntNode first;

    public void addFirst(int x) {
        if (first != null) {
            throw new RuntimeException("You can not execute addFirst() more than once!");
        }
        first = new IntNode(x, first);
    }

    /**
     * mplement SLList.insert which takes in an integer x and
     * inserts it at the given position. If the position is
     * after the end of the list,
     * insert the new node at the end. For example,
     * if the SLList is 5 → 6 → 2,
     * insert(10, 1) results in 5 → 10 → 6 → 2
     * @param item item to insert
     * @param position the position to insert item
     */
    public void insert(int item, int position) {
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }
        IntNode p = first;
        while (p.next != null && position > 1) {
            p = p.next;
            position -= 1;
        }

        p.next = new IntNode(item, p.next);
    }


    /**
     * Add another method to the SLList class that
     * reverses the elements. Do this using the existing
     * IntNodes (you should not use **new** ).
     */
    public void reverseIter() {
        if (first == null) {
            return;
        }
        // 新的链表尾
        IntNode end = null;
        // 指针
        IntNode p = first;
        // 至少执行一次
        while (p != null) {
            // 保存 原链表 指针后的元素
            IntNode rest = p.next;
            // 当前元素指向新链表尾
            p.next = end;
            // 移动新链表尾
            end = p;
            // 移动指针
            p = rest;
        }
        // 裸链表赋值
        first = end;
    }


    public void reverse() {
        if (first == null) {
            return;
        }
        IntNode end = null;
        IntNode p = first;
        if (p.next == null) {
            return;
        }

    }

    @Override
    public int hashCode() {
        return first.hashCode();
    }

    /**
     * 根据可变长度的参数列表构建 SLList
     * @param args 用于构造链表的参数列表
     * @return 代表相应链表的SLList实例
     */
    public static SLList of(Integer... args) {
        if (args.length <= 0) {
            return null;
        }
        IntNode nudeList = null;
        for (int i = args.length - 1; i >= 0; i--) {
            nudeList = new IntNode(args[i], nudeList);
        }
        SLList result = new SLList();
        result.first = nudeList;
        return result;
    }

    @Override
    public boolean equals(Object x) {
        if (!(x instanceof SLList)) {
            return false;
        }
        SLList that = (SLList) x;
        if (!this.toString().equals(that.toString())) {
            return false;
        }
        return this.hashCode() == that.hashCode();
    }

    /**
     * 用于判断数组是否有环的私有工具方法
     * 特别是用于反转链表的测试
     *
     * @return 如没有环 则返回0 否则返回探测出环的位置
     */
    private int detectCycles() {
        IntNode tortoise = first;
        IntNode hare = first;

        if (first == null) {
            return 0;
        }

        int cnt = 0;

        while (true) {
            cnt++;
            if (hare.next != null) {
                hare = hare.next.next;
            } else {
                return 0;
            }

            tortoise = tortoise.next;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles();
        int cnt = 0;

        for (IntNode p = first; p != null; p = p.next) {
            out.format("%s%s", sep, p);
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