package com.learning.lesson04doublelinkedlist;

/**
 * 双向链表节点
 *
 * @author Zongheng Ma
 * @date 2020-4-29
 */
public class DoubleNode {

    /**
     * data域--编号
     */
    public int no;

    /**
     * data域--姓名
     */
    public String name;

    /**
     * data域--诨名
     */
    public String nickName;

    /**
     * 指针域--next指针，指向后继节点
     */
    public DoubleNode next;

    /**
     * 指针域--pre指针，指向前驱节点
     */
    public DoubleNode pre;


    /**
     * 带参构造方法
     *
     * @param no       序号
     * @param name     名称
     * @param nickName 别名
     */
    public DoubleNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }


    @Override
    public String toString() {
        return "{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + "'}";
    }
}
