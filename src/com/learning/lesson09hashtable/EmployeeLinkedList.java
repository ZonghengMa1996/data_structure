package com.learning.lesson09hashtable;

/**
 * 雇员链表
 *
 * @author Zongheng Ma
 * @date 2020-6-28
 */
public class EmployeeLinkedList {

    /**
     * 头指针，指向第一个节点
     */
    private EmployeeNode head;


    /**
     * 添加雇员节点（尾插法）
     *
     * @param node
     */
    public void add(EmployeeNode node) {
        // 判断原链表有无雇员节点
        if (head == null) {
            head = node;
        } else {
            EmployeeNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }


    /**
     * 遍历链表
     *
     * @param no 链表的序号
     */
    public void showList(int no) {
        if (head == null) {
            System.out.printf("第%d个链表为空\n", no + 1);
        } else {
            System.out.printf("第%d个链表的节点如下：", no + 1);
            EmployeeNode cur = head;
            while (cur != null) {
                System.out.print(cur.toString() + (cur.next == null ? "\n" : ", "));
                cur = cur.next;
            }
        }
    }


    /**
     * 根据id查找节点
     *
     * @param id
     * @return
     */
    public EmployeeNode searchById(int id) {
        // 链表为空
        if (head == null) {
            return null;
        }
        EmployeeNode cur = head;
        while (cur != null) {
            if (cur.id == id) {
                return cur;
            }
            cur = cur.next;
        }
        // 遍历完链表之后未找到
        return null;
    }


    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public boolean remove(int id) {
        if (head == null) {
            // 链表空
            return false;
        }
        if (head.id == id) {
            // 头指针所指为待删除节点
            head = head.next;
            return true;
        }
        // 遍历节点
        EmployeeNode cur = head.next;
        // 前驱
        EmployeeNode pre = head;
        while (cur != null) {
            if (cur.id == id) {
                pre.next = cur.next;
                return true;
            }
            // 向后遍历
            cur = cur.next;
            pre = pre.next;
        }
        return false;
    }
}
