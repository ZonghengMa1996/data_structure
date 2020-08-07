package com.learning.lesson06stack;

import com.learning.lesson03singlelinkedlist.Node;

/**
 * 单链表模拟堆栈
 *
 * @author Zongheng Ma
 * @date 2020-5-6
 */
public class LinkedListStack {

    /**
     * 头结点
     */
    public Node head = new Node(0, "", "");

    /**
     * 栈容量
     */
    public int size;


    /**
     * 带参构造方法
     *
     * @param size 栈容量
     */
    public LinkedListStack(int size) {
        this.size = size;
    }


    /**
     * 获取有效元素个数
     *
     * @return int
     */
    public int getSize() {
        if (head.next == null) {
            return 0;
        }
        int count = 0;
        Node temp = head.next;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }


    /**
     * 显示栈内元素
     */
    public void showStack() {
        if (head.next == null) {
            System.out.println("栈空~");
        } else {
            System.out.println("----------------\n栈内元素从顶至底为：");
            Node temp = head.next;
            while (temp != null) {
                System.out.printf("{ no = %d, name = \"%s\" }\n", temp.no, temp.name);
                temp = temp.next;
            }
            System.out.println("----------------");
        }
    }


    /**
     * 入栈
     *
     * @param item 待入栈元素
     */
    public void push(Node item) {
        if (getSize() == size) {
            System.out.println("栈已满");
        } else {
            item.next = head.next;
            head.next = item;
            System.out.printf("元素%s入栈，栈内元素个数为%d\n", item.name, getSize());
        }
    }


    /**
     * 出栈
     */
    public void pop() {
        if (getSize() == 0) {
            System.out.println("栈已空");
        } else {
            Node temp = head.next;
            head.next = temp.next;
            System.out.printf("元素%s出栈，栈内元素个数为%d\n", temp.name, getSize());
        }
    }
}
