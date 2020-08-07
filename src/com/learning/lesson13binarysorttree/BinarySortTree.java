package com.learning.lesson13binarysorttree;

/**
 * 二叉排序树
 *
 * @author Zongheng Ma
 * @date 2020/8/5
 */
public class BinarySortTree {

    public static void main(String[] args) {

    }

    /**
     * 根结点
     */
    private final Node root;


    public BinarySortTree(Node root) {
        this.root = root;
    }


    /**
     * 中序遍历
     */
    public void inOrder() {
        if (root == null) {
            System.out.println("树为空");
        } else {
            System.out.println("----------中序遍历----------");
            root.inOrder();
        }
    }
}
