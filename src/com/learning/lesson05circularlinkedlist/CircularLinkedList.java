package com.learning.lesson05circularlinkedlist;

/**
 * 单循环链表
 *
 * @author Zongheng Ma
 * @date 2020-4-29
 */
public class CircularLinkedList {

    /**
     * first指针，指向第一个节点
     */
    public Node first = new Node(-1);


    /**
     * 初始化链表
     *
     * @param nums 链表节点数
     */
    public CircularLinkedList(int nums) {
        // 判断nums合法性
        if (nums < 1) {
            throw new RuntimeException("nums不正确");
        }

        // 辅助指针
        Node cur = null;
        for (int i = 1; i <= nums; i++) {
            Node newNode = new Node(i);
            // 若为第一个节点，则用first指向
            if (i == 1) {
                first = newNode;
                first.next = first;
                // cur指向first
                cur = first;
            } else {
                cur.next = newNode;
                newNode.next = first;
                cur = newNode;
            }
        }
    }


    /**
     * 遍历链表
     */
    public void showList() {
        if (first == null || first.no == -1) {
            System.out.println("链表为空");
        } else {
            Node cur = first;
            System.out.printf("----------------\n当前链表有%d个节点：\n", getSize());
            // cur向后遍历再判断，可以do-while
            do {
                System.out.print(cur.no + (cur.next == first ? "\n" : "\t"));
                cur = cur.next;
            } while (cur != first);
            System.out.println("----------------");
        }
    }


    /**
     * 获取有效节点数
     *
     * @return int
     */
    public int getSize() {
        if (first.no == -1) {
            return 0;
        }
        Node cur = first;
        int size = 0;
        do {
            size++;
            cur = cur.next;
        } while (cur != first);
        return size;
    }


    /**
     * 尾插法添加一个新节点
     */
    public void addNode() {
        // 获取新节点的编号
        int no = getSize() + 1;
        Node newNode = new Node(no);
        if (first.no == -1) {
            first = newNode;
        } else {
            Node cur = first;
            while (cur.next != first) {
                cur = cur.next;
            }
            cur.next = newNode;
        }
        newNode.next = first;
    }


    /**
     * 尾插法添加多个节点
     *
     * @param nums 添加的节点个数
     */
    public void addNodes(int nums) {
        for (int i = 1; i <= nums; i++) {
            addNode();
        }
        System.out.printf("已添加%d个节点至链表\n", nums);
    }
}
