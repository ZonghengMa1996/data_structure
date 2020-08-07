package com.learning.lesson03singlelinkedlist;

import java.util.Stack;

/**
 * 单链表
 *
 * @author Zongheng Ma
 * @date 2020-4-27
 */
public class SingleLinkedList {

    /**
     * 头节点
     */
    public Node head = new Node(0, "", "");


    /**
     * 默认不带参构造方法
     */
    public SingleLinkedList() {

    }


    /**
     * 带参构造方法
     *
     * @param newHead 新表头
     */
    public SingleLinkedList(Node newHead) {
        head = newHead;
    }


    /**
     * 添加节点
     *
     * @param node 待添加节点
     */
    public void add(Node node) {
        // temp指向head，作为辅助变量遍历单链表
        Node temp = head;
        // 若next空，则为最后一个节点
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }


    /**
     * 获取单链表中有效节点个数
     *
     * @param head 链表的头结点
     * @return int
     */
    public static int getSize(Node head) {
        Node temp = head.next;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }


    /**
     * 显示单链表中节点
     */
    public void showList() {
        // 遍历单链表的辅助变量
        Node temp = head.next;

        // 判断队是否空
        if (temp != null) {
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
     * 根据序号顺序添加节点
     *
     * @param node 待添加节点
     */
    public void addByOrder(Node node) {
        // 遍历用的指针
        Node temp = head;
        // 序号是否已存在的标志
        boolean flag = false;

        // 1.寻找新节点位置的前驱节点
        // 2.找到合适位置(两种情况)：
        //   （1）前驱.next.data > 新节点.data
        //   （2）遍历至最后一个节点，则加到队尾
        // 3.操作 前驱.next=新节点 新节点.next=前驱.next
        // 4.若找到序号相同节点，则将flag置为true，跳出循环
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        // 判断是否有序号相同节点
        if (flag) {
            System.out.printf("序号%d已存在，节点%s添加失败\n", node.no, node.name);
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }


    /**
     * 根据序号删除
     *
     * @param no 待删除节点的序号
     */
    public void deleteByNo(int no) {
        boolean flag = false;
        Node temp = head;

        // 1.寻找待删除节点的前驱节点
        // 2.前驱.next = 待删除.next
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                flag = true;
                System.out.printf("序号为%d的节点已被删除\n", no);
                System.out.printf("当前链表中有效节点个数为：%d\n",
                        getSize(head));
                showList();
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.printf("未找到序号为%d的节点\n", no);
        }
    }


    /**
     * 根据序号修改
     *
     * @param node
     */
    public void updateByNo(Node node) {
        // 判断队空
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }

        // 遍历辅助指针
        Node temp = head.next;
        // 是否找到节点的flag
        boolean flag = false;

        while (temp != null) {
            if (temp.no == node.no) {
                temp.name = node.name;
                temp.nickName = node.nickName;
                flag = true;
                System.out.printf("序号为%d的节点已更新：%s\n",
                        node.no, node.toString());
                System.out.printf("当前链表中有效节点个数为：%d\n",
                        getSize(head));
                showList();
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.printf("未找编号为%d到节点\n", node.no);
        }
    }


    /**
     * 获取倒数第n个节点【新浪面试题】
     *
     * @param listHead 头节点
     * @param index    倒数索引
     * @return 节点
     */
    public static Node getCountBackwards(Node listHead, int index) {
        // 判断链表是否空
        if (listHead.next == null) {
            throw new RuntimeException("链表为空\n");
        }
        // 判断index是否合法
        int size = getSize(listHead);
        if (index <= 0 || index > size) {
            throw new RuntimeException("不存在倒数第" + index + "个节点\n");
        }

        // 遍历链表的辅助变量
        Node temp = listHead.next;
        // int i=0; i<size-index; 亦可
        for (int i = 1; i < size - index + 1; i++) {
            temp = temp.next;
        }
        return temp;
    }


    /**
     * 单链表的反转
     *
     * @return 反转后的链表
     */
    public void reverse() {
        if (head.next == null || head.next.next == null) {
            throw new RuntimeException("链表空或只有一个节点，无需反转");
        }

        // 遍历链表的辅助指针
        Node cur = head.next;
        // 反转链表的head
        Node reverseHead = new Node(0, "", "");

        Node newTemp;
        while (cur != null) {
            // 将cur当前指向赋给新节点
            newTemp = cur;
            // cur后移
            cur = cur.next;
            // 新节点放入反转链表首位
            newTemp.next = reverseHead.next;
            reverseHead.next = newTemp;
        }
        head.next = reverseHead.next;
        System.out.println("当前链表已反转");
    }


    /**
     * 单链表的反转（韩老师给出的方法）
     */
    public void reverseStandard() {
        // 遍历链表的辅助指针
        Node cur = head.next;
        // 反转链表的head
        Node reverseHead = new Node(0, "", "");

        // next保留cur的后继
        Node next;
        while (cur != null) {
            // next指向cur的后继，防止断链
            next = cur.next;
            // 将cur放入反转链表的首位
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            // cur后移
            cur = next;
        }
        head.next = reverseHead.next;
        System.out.println("当前链表已反转");
    }


    /**
     * 倒序打印单链表
     */
    public void reversePrint() {
        if (head.next == null) {
            throw new RuntimeException("当前单链表为空，无需打印");
        }
        // 辅助栈实现逆序打印
        Stack<Node> nodeStack = new Stack<>();
        Node cur = head.next;
        while (cur != null) {
            nodeStack.add(cur);
            cur = cur.next;
        }

        System.out.println("当前链表的逆序打印为：");
        while (nodeStack.size() > 0) {
            System.out.println(nodeStack.pop().toString());
        }
    }


    /**
     * 将两个有序单链表合并为一个有序单链表
     *
     * @param head1 链表1的头结点
     * @param head2 链表2的头结点
     * @return 新的有序单链表
     */
    public static SingleLinkedList listMerge(Node head1, Node head2) {

        // 遍历单链表1的指针
        Node cur1 = head1.next;
        Node next1;
        // 遍历单链表2的指针
        Node cur2 = head2.next;
        Node next2;
        // 新链表的头结点
        Node newHead = new Node(0, "", "");

        // 同时遍历两个单链表
        while (cur1 != null && cur2 != null) {
            // 将序号较小的放入新链表
            if (cur1.no < cur2.no) {
                next1 = cur1.next;
                cur1.next = newHead.next;
                newHead.next = cur1;
                cur1 = next1;
            } else if (cur1.no > cur2.no) {
                next2 = cur2.next;
                cur2.next = newHead.next;
                newHead.next = cur2;
                cur2 = next2;
            } else {
                // 序号相等，则取其一，两个遍历指针同时后移
                next1 = cur1.next;
                cur1.next = newHead.next;
                newHead.next = cur1;
                cur1 = next1;
                cur2 = cur2.next;
            }
        }
        // 将尚未遍历完的链表加入新链表
        if (cur1 != null) {
            while (cur1 != null) {
                next1 = cur1.next;
                cur1.next = newHead.next;
                newHead.next = cur1;
                cur1 = next1;
            }
        } else if (cur2 != null) {
            while (cur2 != null) {
                next2 = cur2.next;
                cur2.next = newHead.next;
                newHead.next = cur2;
                cur2 = next2;
            }
        }

        // 头插法所得新链表
        return new SingleLinkedList(newHead);
    }
}
