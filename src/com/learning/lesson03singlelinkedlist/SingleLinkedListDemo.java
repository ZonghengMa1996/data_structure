package com.learning.lesson03singlelinkedlist;

/**
 * 单链表 演示实例
 *
 * @author Zongheng Ma
 * @date 2020-4-27
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        // 创建新节点
        Node node1 = new Node(1, "node1", "");
        Node node2 = new Node(2, "node2", "");
        Node node3 = new Node(3, "node3", "");
        Node node4 = new Node(4, "node4", "");
        Node node5 = new Node(5, "node5", "");
        Node node6 = new Node(6, "node6", "");
        Node node7 = new Node(7, "node7", "");

        // 创建单链表，按序号顺序添加节点
        SingleLinkedList list1 = new SingleLinkedList();
        list1.addByOrder(node7);
        list1.addByOrder(node1);
        list1.addByOrder(node3);
        list1.addByOrder(node5);

        // 显示倒数第n个节点
        int index = 3;
        try {
            Node target = SingleLinkedList.getCountBackwards(list1.head, index);
            System.out.printf("链表中倒数第%d个节点为%s\n", index, target.name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 单链表逆序打印
        try {
            list1.reversePrint();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 单链表反转
        try {
            list1.reverse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        list1.showList();

        // 有序链表合并为新的有序链表
        SingleLinkedList list2 = new SingleLinkedList();
        list2.addByOrder(node6);
        list2.addByOrder(node2);
        list2.addByOrder(node4);
        SingleLinkedList newList = SingleLinkedList.listMerge(list1.head, list2.head);
        // 头插法所得新链表，顺序与原链表相反，故先反转再打印
        newList.reverse();
        newList.showList();
    }
}

