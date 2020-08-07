package com.learning.lesson05circularlinkedlist;

import java.util.Scanner;

/**
 * 单循环链表解决约瑟夫环问题
 *
 * @author Zongheng Ma
 * @date 2020-4-29
 */
public class JosephRingDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入链表节点数
        System.out.print("输入初始节点数：");
        int nodeNum = scanner.nextInt();
        // 输入报数起始节点的编号
        System.out.print("从第几个节点开始报数：");
        int startNo = scanner.nextInt();
        // 输入每一轮报数次数
        System.out.print("每轮报数的第几个节点出列：");
        int countNum = scanner.nextInt();
        scanner.close();

        try {
            josephRing(nodeNum, startNo, countNum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * 约瑟夫环处理函数
     *
     * @param nodeNum 链表的初始节点数
     * @param startNo 从第几个节点开始遍历
     * @param countNo 每一轮报数的次数
     */
    public static void josephRing(int nodeNum, int startNo, int countNo) {
        if (nodeNum < 1) {
            throw new RuntimeException("链表节点数不可小于1！");
        }
        // 创建链表
        CircularLinkedList josephRing = new CircularLinkedList(nodeNum);
        if (startNo > nodeNum) {
            throw new RuntimeException("遍历起始节点编号不能超过链表节点数！");
        }

        // 每一轮报数的起始节点，每一轮报数后指向待删除节点
        Node starter = josephRing.first;
        // 用来暂存待删除前驱的辅助指针
        Node helper = starter.next;
        // 先将helper定位至链表中最后一个元素（即starter的前驱位置）
        while (helper.next != josephRing.first) {
            helper = helper.next;
        }
        // 根据startNo确定starter和helper的起始位置
        for (int i = 1; i < startNo; i++) {
            starter = starter.next;
            helper = helper.next;
        }

        // 报数
        /* 关于 josephRing.getSize()>1
         * 计算size需要从first所指向节点从头至尾循环遍历链表
         * 而报数过程中first所指节点会在某一轮中出列，造成getSize的循环中始终无法达成cur.next=first这个条件而无法跳出
         * 所以每一轮循环之后需要让first重新指向starter (first = starter;)
         */
        /* 关于 helper != starter
         * 倒数第二个节点出列钱，链表中只剩starter和helper两个节点；
         * 由于是循环链表，helper既是starter的前驱也是其后继；
         * 因此，starter出列后 starter = starter.next
         * 此时starter与helper指向相同，即链表中最后一个节点
         * 相比getSize()，省去了计算size的循环以及first的重新指向，故此方法更优！
         */
        while (helper != starter) {
            // 每一轮报数，helper和starter都后移countNum-1次
            for (int i = 1; i <= countNo - 1; i++) {
                helper = helper.next;
                starter = starter.next;
            }
            // 每一轮结束后，starter所指即为待出列节点
            System.out.printf("节点%d出列！\n", starter.no);
            // starter所指节点删除
            starter = starter.next;
            helper.next = starter;
        }
        // 最后一个节点打印出列
        System.out.printf("节点%d最后一个出列！\n", starter.no);
    }
}
