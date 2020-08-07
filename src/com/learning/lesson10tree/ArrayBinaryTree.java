package com.learning.lesson10tree;

/**
 * 顺序存储二叉树
 *
 * @author Zongheng Ma
 * @date 2020-6-30
 */
public class ArrayBinaryTree {

    public static void main(String[] args) {
        // 创建节点
        BinaryTreeNode node1 = new BinaryTreeNode(1, "a");
        BinaryTreeNode node2 = new BinaryTreeNode(2, "b");
        BinaryTreeNode node3 = new BinaryTreeNode(3, "c");
        BinaryTreeNode node4 = new BinaryTreeNode(4, "d");
        BinaryTreeNode node5 = new BinaryTreeNode(5, "e");
        BinaryTreeNode node6 = new BinaryTreeNode(6, "f");
        BinaryTreeNode node7 = new BinaryTreeNode(7, "g");
        BinaryTreeNode node8 = new BinaryTreeNode(8, "h");
        BinaryTreeNode node9 = new BinaryTreeNode(9, "i");
        // 顺序存储二叉树初始化
        BinaryTreeNode[] nodeArr = {node1, node2, node3, node4, node5, node6, node7, node8, node9};
        ArrayBinaryTree arrTree = new ArrayBinaryTree(nodeArr);
        // 遍历
        arrTree.traversal();
    }


    /**
     * 节点数组
     */
    private final BinaryTreeNode[] array;

    public ArrayBinaryTree(BinaryTreeNode[] arr) {
        this.array = arr;
    }


    /**
     * 遍历方法
     */
    public void traversal() {
        if (array == null || array.length == 0 || array[0] == null) {
            // 数组未初始化、数组元素个数为0、根节点arr[0]为空
            System.out.println("树为空");
        } else {
            System.out.println("------先序------");
            preOrder(0);
            System.out.println("------中序------");
            inOrder(0);
            System.out.println("------后序------");
            postOrder(0);
        }
    }


    /**
     * 前序遍历
     *
     * @param index 数组下标
     */
    private void preOrder(int index) {
        // 根
        System.out.println(array[index].toString());
        // 左
        int lIndex = 2 * index + 1;
        if (lIndex <= array.length - 1 && array[lIndex] != null) {
            preOrder(lIndex);
        }
        // 右
        int rIndex = 2 * index + 2;
        if (rIndex <= array.length - 1 && array[rIndex] != null) {
            preOrder(rIndex);
        }
    }


    /**
     * 中序遍历
     *
     * @param index 数组下标
     */
    private void inOrder(int index) {
        // 左
        int lIndex = 2 * index + 1;
        if (lIndex <= array.length - 1 && array[lIndex] != null) {
            inOrder(lIndex);
        }
        // 根
        System.out.println(array[index].toString());
        // 右
        int rIndex = 2 * index + 2;
        if (rIndex <= array.length - 1 && array[rIndex] != null) {
            inOrder(rIndex);
        }
    }


    /**
     * 后序遍历
     *
     * @param index 数组下标
     */
    private void postOrder(int index) {
        // 左
        int lIndex = 2 * index + 1;
        if (lIndex <= array.length - 1 && array[lIndex] != null) {
            postOrder(lIndex);
        }
        // 右
        int rIndex = 2 * index + 2;
        if (rIndex <= array.length - 1 && array[rIndex] != null) {
            postOrder(rIndex);
        }
        // 根
        System.out.println(array[index].toString());
    }
}
