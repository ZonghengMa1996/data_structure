package com.learning.lesson03singlelinkedlist;

/**
 * 单链表节点
 *
 * @author Zongheng Ma
 * @date 2020-4-27
 */
public class Node {

    /**
     * data域-编号
     */
    public int no;

    /**
     * data域-姓名
     */
    public String name;

    /**
     * data域-诨名
     */
    public String nickName;

    /**
     * next域，指向后继节点
     */
    public Node next;


    /**
     * 带参构造方法
     *
     * @param no       序号
     * @param name     名称
     * @param nickName 别名
     */
    public Node(int no, String name, String nickName) {
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
