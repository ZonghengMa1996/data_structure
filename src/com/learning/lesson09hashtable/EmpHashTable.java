package com.learning.lesson09hashtable;

import java.util.Scanner;

/**
 * 雇员哈希表
 *
 * @author Zongheng Ma
 * @date 2020-6-28
 */
public class EmpHashTable {

    public static void main(String[] args) {
        // 创建哈希表
        EmpHashTable hashTable = new EmpHashTable(3);
        // 控制台菜单
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add  添加节点");
            System.out.println("del  删除节点");
            System.out.println("list 遍历哈希表");
            System.out.println("find 根据id查找");
            System.out.println("exit 退出");
            System.out.println("请输入指令：");
            String key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("输入id：");
                    int id = scanner.nextInt();
                    System.out.print("输入名称：");
                    String name = scanner.next();
                    EmployeeNode newNode = new EmployeeNode(id, name);
                    hashTable.add(newNode);
                    break;
                case "del":
                    System.out.print("请输入id：");
                    int nodeId = scanner.nextInt();
                    hashTable.removeById(nodeId);
                    break;
                case "list":
                    hashTable.showAll();
                    break;
                case "find":
                    System.out.print("请输入id：");
                    int i = scanner.nextInt();
                    hashTable.findById(i);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(1);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 链表数组中链表的个数
     */
    private final Integer size;

    /**
     * 链表数组
     */
    private final EmployeeLinkedList[] linkedListArr;


    public EmpHashTable(int size) {
        this.size = size;
        this.linkedListArr = new EmployeeLinkedList[size];
        // 分别初始化每个链表（否则会NullPointerException）
        for (int i = 0; i < linkedListArr.length; i++) {
            linkedListArr[i] = new EmployeeLinkedList();
        }
    }


    /**
     * 添加节点
     *
     * @param node
     */
    public void add(EmployeeNode node) {
        // 散列函数：根据id计算对应链表的索引
        int index = hashFunction(node.id);
        linkedListArr[index].add(node);
    }


    /**
     * 散列函数（取模法）
     *
     * @param id
     * @return 链表的索引
     */
    public int hashFunction(int id) {
        return id % size;
    }


    /**
     * 遍历所有链表
     */
    public void showAll() {
        for (int i = 0; i < size; i++) {
            linkedListArr[i].showList(i);
        }
    }


    /**
     * 根据id查找节点
     *
     * @param id 节点id
     */
    public void findById(int id) {
        // 散列函数计算对应的链表序号
        int index = hashFunction(id);
        // 调用对应链表的查找方法
        EmployeeNode node = linkedListArr[index].searchById(id);
        if (node != null) {
            System.out.printf("在第%d个链表中找到：%s\n", index + 1, node.toString());
        } else {
            System.out.printf("未找到id为%d的节点\n", id);
        }
    }


    /**
     * 根据id删除
     *
     * @param id
     */
    public void removeById(int id) {
        int index = hashFunction(id);
        if (linkedListArr[index].remove(id)) {
            System.out.printf("已删除id为%d的节点\n", id);
        } else {
            System.out.printf("未找到id为%d的节点\n", id);
        }
    }
}
