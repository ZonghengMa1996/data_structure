package com.learning.lesson04doublelinkedlist;

/**
 * 双向链表
 *
 * @author Zongheng Ma
 * @date 2020-4-29
 */
public class DoubleLinkedList {

    /**
     * 头结点
     */
    public DoubleNode head = new DoubleNode(0, "", "");


    /**
     * 显示单链表中节点
     */
    public void showList() {
        // 遍历单链表的辅助变量
        DoubleNode temp = head.next;

        // 判断队是否空
        if (temp != null) {
            System.out.println("--------------------------------");
            System.out.println("当前链表中节点如下：");
            // 此处若为temp.next != null会导致遍历至最后一个节点时循环终止，最后一个节点无法打印
            while (temp != null) {
                System.out.println(temp.toString());
                temp = temp.next;
            }
            System.out.println("--------------------------------");
        } else {
            System.out.println("当前链表为空");
        }
    }


    /**
     * 按序号升序添加节点
     *
     * @param newNode 待添加节点
     */
    public void addByOrder(DoubleNode newNode) {
        // 遍历链表的指针
        DoubleNode temp = head;
        // 是否找到序号相同节点
        boolean flag = false;

        // 查找添加位置
        while (temp.next != null) {
            if (temp.next.no > newNode.no) {
                break;
            } else if (temp.next.no == newNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 是否找到序号相同节点
        if (flag) {
            System.out.printf("当前链表中已存在序号为%d的节点\n", newNode.no);
        } else {
            if (temp.next != null) {
                temp.next.pre = newNode;
            }
            newNode.pre = temp;
            newNode.next = temp.next;
            temp.next = newNode;
            System.out.printf("节点%s已按顺序添加到链表\n", newNode.name);
        }
    }


    /**
     * 头插法
     *
     * @param newNode 待添加节点
     */
    public void addToTail(DoubleNode newNode) {
        // temp指向head，作为辅助变量遍历单链表
        DoubleNode temp = head;

        // 遍历至最后一个节点
        while (temp.next != null) {
            temp = temp.next;
        }

        // temp的后继指向新节点
        temp.next = newNode;
        // 新节点的前驱指向temp
        newNode.pre = temp;
        System.out.printf("节点%s已添加至链表尾部\n", newNode.name);
    }


    /**
     * 尾插法
     *
     * @param newNode 待添加节点
     */
    public void addToHead(DoubleNode newNode) {
        // 若链表中有节点，则将原第一个节点的前驱指向新节点
        if (head.next != null) {
            head.next.pre = newNode;
        }
        newNode.pre = head;
        newNode.next = head.next;
        head.next = newNode;
        System.out.printf("节点%s已添加至链表头部\n", newNode.name);
    }


    /**
     * 根据序号删除节点
     *
     * @param no 节点序号
     */
    public void deleteByNo(int no) {
        // 遍历链表的指针
        DoubleNode temp = head.next;
        if (temp == null) {
            throw new RuntimeException("链表为空");
        }

        // 是否找到待删除节点的flag
        boolean flag = false;
        // 遍历查找待删除节点
        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 是否找到待删除节点
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
            System.out.printf("已删除序号为%d的节点\n", no);
        } else {
            System.out.printf("未找到序号为%d的节点\n", no);
        }
    }
}
