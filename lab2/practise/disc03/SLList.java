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
        /*
         * 如果插入位置是n 则应循环 n-1 次
         * 链表尽头也停止
         */
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
        IntNode newList = null;
        IntNode pointer = first;
        while (pointer != null) {
            // 保留剩余链表 游离出指针
            IntNode rest = pointer.next;
            // 将指针指向新链表
            pointer.next = newList;
            // 更新新数组
            newList = pointer;
            // 更新指针
            pointer = rest;
        }

        // 另一种解法
        // while (first != null) {
        //     IntNode newListFirst = first;
        //     first = first.next;
        //     newListFirst.next = newList;
        //     newList = newListFirst;
        // }
        // 更新裸链表
        first = newList;
    }


    public void reverse() {
        if (first == null) {
            return ;
        }
        first = reverseHelper(first);
    }

    /**
     * 递归翻转 nude linked list 的帮助函数
     * @param oldList 待操作的 nude linked list
     * @return 被翻转后的 nude linked list
     */
    private IntNode reverseHelper(IntNode oldList) {
        if (oldList == null || oldList.next == null) {
            return oldList;
        }
        // 旧链表尾部
        IntNode newList = reverseHelper(oldList.next);
        // 翻转前两个元素
        oldList.next.next = oldList;
        // 原链表头变为新链表尾部
        oldList.next = null;
        // 返回值不断后移，直到旧链表尾部
        return newList;
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