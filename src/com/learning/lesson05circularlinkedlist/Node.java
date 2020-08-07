package com.learning.lesson05circularlinkedlist;

/**
 * 链表节点
 *
 * @author Zongheng Ma
 * @date 2020-4-29
 */
public class Node {

    /**
     * 编号
     */
    public int no;

    /**
     * 指向后继
     */
    public Node next;

    /**
     * 带参构造方法
     *
     * @param no
     */
    public Node(int no) {
        this.no = no;
    }
}
