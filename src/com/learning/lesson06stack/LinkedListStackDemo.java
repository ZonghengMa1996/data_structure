package com.learning.lesson06stack;

import com.learning.lesson03singlelinkedlist.Node;

/**
 * 单链表模拟堆栈演示
 *
 * @author Zongheng Ma
 * @date 2020-5-6
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {
        // 新建节点
        Node node1 = new Node(1, "node1", "node1");
        Node node2 = new Node(2, "node2", "node2");
        Node node3 = new Node(3, "node3", "node3");
        Node node4 = new Node(4, "node4", "node4");

        // 创建链表栈
        LinkedListStack stack = new LinkedListStack(3);
        // 元素入栈
        stack.push(node1);
        stack.push(node2);
        stack.push(node3);
        stack.push(node4);
        stack.showStack();

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}
