package com.learning.lesson10tree;

import com.common.utils.Constant;

/**
 * 线索化二叉树
 *
 * @author ZongHeng Ma
 * @date 2020-7-1
 */
public class ThreadedBinaryTree extends BinaryTree {

    public static void main(String[] args) {

        // 创建节点
        BinaryTreeNode node1 = new BinaryTreeNode(1, "a");
        BinaryTreeNode node2 = new BinaryTreeNode(2, "b");
        BinaryTreeNode node3 = new BinaryTreeNode(3, "c");
        BinaryTreeNode node4 = new BinaryTreeNode(4, "d");
        BinaryTreeNode node5 = new BinaryTreeNode(5, "e");
        BinaryTreeNode node6 = new BinaryTreeNode(6, "f");

        // 构建二叉树
        ThreadedBinaryTree threadedTree = new ThreadedBinaryTree(node1);
        node1.setLeftChild(node2);
        node1.setRightChild(node3);
        node2.setLeftChild(node4);
        node2.setRightChild(node5);
        node3.setLeftChild(node6);

        // 中序线索化
        threadedTree.preOrderThread();
        threadedTree.preOrder();
    }

    /**
     * 线索化二叉树时的前驱节点
     */
    private BinaryTreeNode pre;

    public ThreadedBinaryTree(BinaryTreeNode node) {
        super(node);
        pre = null;
    }


    /**
     * 重载中序线索化方法
     */
    public void inOrderThread() {
        inOrderThread(root);
    }


    /**
     * 对当前节点线索化（中序）
     *
     * @param node 当前节点
     */
    public void inOrderThread(BinaryTreeNode node) {
        // 判断当前节点是否空
        if (node != null) {
            // 线索化左子树
            inOrderThread(node.getLeftChild());

            // 线索化当前节点
            if (!node.hasLeftChild()) {
                // 左子节点指向遍历前驱
                node.setLeftChild(pre);
                // 左线索标记置为true
                node.left = Constant.PointerType.Thread;
            }
            if (pre != null && !pre.hasRightChild()) {
                // 前驱节点的右指针指向当前节点
                pre.setRightChild(node);
                // 前驱节点的右线索标记置true
                pre.right = Constant.PointerType.Thread;
            }
            // 将下一个节点的前驱节点指向当前
            pre = node;

            // 线索化右子树
            inOrderThread(node.getRightChild());
        }
    }


    /**
     * 线索化之后的中序遍历
     */
    @Override
    public void inOrder() {
        if (root == null) {
            System.out.println("树为空");
        } else {
            System.out.println("===线索化之后的中序遍历===");
            // 遍历指针
            BinaryTreeNode p = root;
            while (p != null) {
                // 遍历左子树，直至找到一个左线索化的节点
                while (p.left == Constant.PointerType.Child) {
                    p = p.getLeftChild();
                }
                System.out.println(p.toString());
                // 右指针指向后继节点，直接向后遍历
                while (p.right == Constant.PointerType.Thread) {
                    p = p.getRightChild();
                    System.out.println(p.toString());
                }
                p = p.getRightChild();
            }
        }
    }


    /**
     * 重载先序线索化二叉树的方法
     */
    public void preOrderThread() {
        preOrderThread(root);
    }


    /**
     * 先序线索化二叉树
     *
     * @param node 当前节点
     */
    public void preOrderThread(BinaryTreeNode node) {
        // 判断当前节点是否为空
        if (node != null) {
            // 线索化当前节点
            if (!node.hasLeftChild()) {
                // 节点无左子节点，将左指针指向前驱，线索标志置true
                node.setLeftChild(pre);
                node.left = Constant.PointerType.Thread;
            }
            if (pre != null && !pre.hasRightChild()) {
                // 前驱不空且无右子节点，将其右线索置true，后继指向当前节点
                pre.setRightChild(node);
                pre.right = Constant.PointerType.Thread;
            }
            // 当前节点线索化完成
            pre = node;

            // 线索化左子树
            if (node.left != Constant.PointerType.Thread) {
                preOrderThread(node.getLeftChild());
            }

            // 线索化右子树
            if (node.right != Constant.PointerType.Thread) {
                preOrderThread(node.getRightChild());
            }
        }
    }


    /**
     * 线索化后的先序遍历
     */
    public void preOrder() {
        BinaryTreeNode p = root;
        while (p != null) {
            System.out.println(p.toString());
            if (p.hasLeftChild() && p.left == Constant.PointerType.Child) {
                p = p.getLeftChild();
            } else {
                p = p.getRightChild();
            }
        }
    }


    /**
     * 重载后序线索化二叉树的方法
     */
    public void postOrderThread() {
        postOrderThread(root);
    }


    /**
     * 后序线索化二叉树
     *
     * @param node 当前节点
     */
    public void postOrderThread(BinaryTreeNode node) {
        if (node != null) {
            // 线索化左子树
            postOrderThread(node.getLeftChild());

            // 线索化右子树
            postOrderThread(node.getRightChild());

            if (!node.hasLeftChild()) {
                node.setLeftChild(pre);
                node.left = Constant.PointerType.Thread;
            }
            if (pre != null && !pre.hasRightChild()) {
                pre.setRightChild(node);
                pre.right = Constant.PointerType.Thread;
            }
            pre = node;
        }
    }
}