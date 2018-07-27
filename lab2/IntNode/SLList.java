/**
 * Singly linked list
 * 单向链表 封装递归细节
 */
public class SLList {

    private static class IntNode{
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    // 指定的前哨节点
    private IntNode sentinel;
    private int size;

    /**
     * 空链表
     * size 初始化为 0
     * first 初始化为 null
     */
    public SLList() {
        sentinel = new IntNode(Integer.MAX_VALUE, null);
    }

    public SLList(int x) {
        this.sentinel = new IntNode(Integer.MAX_VALUE, null);
        this.sentinel.next = new IntNode(x, null);
        this.size = 1;
    }

    /**
     * 向链表的头部添加元素
     * @param x 元素值
     */
    public void addFirst(int x) {
        this.sentinel.next = new IntNode(x, this.sentinel.next);
        this.size += 1;
    }

    /**
     * 获取链表首部的值
     * @return 链表第一个元素的值
     */
    public int getFirst() {
        return this.sentinel.next.item;
    }


    /**
     * 向链表皆为添加数据
     * @param x 待添加数据
     */
    public void addLast(int x) {
        this.size += 1;
        IntNode c = this.sentinel;
        while (c.next != null) {
            c = c.next;
        }
        c.next = new IntNode(x, null);
    }

    /**
     * 递归求解指定 IntNode 的长度
     * @param n 传入的 IntNode 对象
     * @return 裸链表长度
     */
//    private static int size(IntNode n){
//        if (n.next == null) {
//            return 1;
//        }
//        return 1 + SLList.size(n.next);
//    }

    /**
     * 求解当前链表长度
     * @return 链表长度
     */
//    public int size() {
//        return SLList.size(this.first);
//    }

    /**
     * 迭代式获取链表长度
     * @return 链表长度
     */
//    public int sizeIter() {
//        IntNode c = this.first;
//        int size = 1;
//        while (c.next != null) {
//            c = c.next;
//            size += 1;
//        }
//        return size;
//    }

    /**
     * 获取当前链表长度
     * @return 链表长度
     */
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        SLList l = new SLList();
        System.out.println(l.size());
        l.addFirst(10);
        l.addFirst(5);
        l.addLast(20);
        System.out.println(l.getFirst());
        System.out.println(l.size());
    }
}
