package com.learning.lesson04doublelinkedlist;

/**
 * 双向链表的演示实例
 *
 * @author Zongheng Ma
 * @date 2020-4-29
 */
public class DoubleLinkedListDemo {

    public static void main(String[] a) {
        // 新建节点
        DoubleNode node1 = new DoubleNode(1, "node1", "node1");
        DoubleNode node2 = new DoubleNode(2, "node2", "node2");
        DoubleNode node3 = new DoubleNode(3, "node3", "node3");
        DoubleNode node4 = new DoubleNode(4, "node4", "node4");

        // 按顺序添加节点
        DoubleLinkedList list1 = new DoubleLinkedList();
        list1.addByOrder(node3);
        list1.addByOrder(node1);
        list1.addByOrder(node4);
        list1.addByOrder(node2);
        list1.addByOrder(node2);
        // 显示链表
        list1.showList();
    }
}
