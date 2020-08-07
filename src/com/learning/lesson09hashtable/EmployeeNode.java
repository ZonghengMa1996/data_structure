package com.learning.lesson09hashtable;

/**
 * 雇员链表节点
 *
 * @author Zongheng Ma
 * @date 2020-6-28
 */
public class EmployeeNode {

    /**
     * id
     */
    public Integer id;

    /**
     * 姓名
     */
    public String name;

    /**
     * 后继节点
     */
    public EmployeeNode next;

    public EmployeeNode(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
