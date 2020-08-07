package com.learning.lesson10tree;

import com.common.utils.Constant;

import java.util.*;

/**
 * 二叉树
 *
 * @author Zongheng Ma
 * @date 2020-6-29
 */
public class BinaryTree {

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

        // 构造二叉树
        node1.setLeftChild(node2);
        node1.setRightChild(node3);
        node2.setLeftChild(node4);
        node2.setRightChild(node5);
        node3.setLeftChild(node6);
        node3.setRightChild(node7);
        node4.setLeftChild(node8);
        node4.setRightChild(node9);
        BinaryTree tree = new BinaryTree(node1);
        // 遍历
        tree.postOrder();
        // 查找
        int no = 9;
        BinaryTreeNode res = tree.searchByNo(no);
        if (res == null) {
            System.out.printf("为找到编号为%d的节点\n", no);
        } else {
            System.out.printf("编号%d的节点为 %s\n", no, res.toString());
        }
        // 根据编号删除
        int deleteNo = 3;
        if (tree.deleteByNo(deleteNo)) {
            System.out.printf("成功删除了编号为%d的节点\n", deleteNo);
            tree.preOder();
        } else {
            System.out.printf("未能删除编号为%d的节点\n", deleteNo);
        }
    }

    /**
     * 根节点
     */
    public BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode node) {
        this.root = node;
    }


    /**
     * 先序遍历
     */
    private void preOder() {
        if (root == null) {
            System.out.println("二叉树为空");
        } else {
            System.out.println("----先序遍历(非递归)----");
            preOrderNoRec();
        }
    }


    /**
     * 先序遍历（递归）
     *
     * @param node
     */
    private void preOrderRec(BinaryTreeNode node) {
        System.out.println(node.toString());
        // 左子节点不空，向左子树递归
        if (node.hasLeftChild()) {
            preOrderRec(node.getLeftChild());
        }
        // 右子节点不空，向右子树递归
        if (node.hasRightChild()) {
            preOrderRec(node.getRightChild());
        }
    }


    /**
     * 先序遍历（非递归）
     */
    public void preOrderNoRec() {
        Stack<BinaryTreeNode> nodeStack = new Stack<>();
        // 根节点入队
        nodeStack.add(root);
        // 遍历用辅助指针
        BinaryTreeNode temp;
        while (!nodeStack.isEmpty()) {
            temp = nodeStack.pop();
            // 先访问当前节点
            System.out.println(temp.toString());
            /*
             * 由于栈具有先进后出的特点，为了保证先左孩后右孩的遍历顺序
             * 此处子节点入栈时，右子节点先入栈，左子节点后入栈
             */
            // 右子节点不空，入栈
            if (temp.hasRightChild()) {
                nodeStack.push(temp.getRightChild());
            }
            // 左子节点不空，入栈
            if (temp.hasLeftChild()) {
                nodeStack.push(temp.getLeftChild());
            }
        }
    }


    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
        } else {
            System.out.println("----后序遍历(非递归)----");
            postOrderNoRec2();
        }
    }


    /**
     * 后序遍历（递归）
     *
     * @param node 节点
     */
    private void postOrderRec(BinaryTreeNode node) {
        if (node.hasLeftChild()) {
            postOrderRec(node.getLeftChild());
        }
        if (node.hasRightChild()) {
            postOrderRec(node.getRightChild());
        }
        System.out.println(node.toString());
    }


    /**
     * 后序遍历（非递归）
     *
     * @description 使用lastVisit记录上一次访问的节点：若lastVisit为右子节点，则访问根节点
     */
    private void postOrderNoRec() {
        Stack<BinaryTreeNode> nodeStack = new Stack<>();
        BinaryTreeNode cur = root, lastVisit = null;
        // 遍历左子树直至末端，并依次将节点入栈
        while (cur != null) {
            nodeStack.push(cur);
            cur = cur.getLeftChild();
        }
        while (!nodeStack.isEmpty()) {
            cur = nodeStack.pop();
            if (cur.getRightChild() == null || cur.getRightChild() == lastVisit) {
                // 仅当右子节点为空或者右子节点已被访问时，访问根
                System.out.println(cur.toString());
                lastVisit = cur;
            } else {
                // 右子节点未被访问，则根重新入栈
                nodeStack.push(cur);
                // 转入右子树
                cur = cur.getRightChild();
                // 再次向左遍历子树直至末端
                while (cur != null) {
                    nodeStack.push(cur);
                    cur = cur.getLeftChild();
                }
            }
        }
    }


    /**
     * 后序遍历（非递归）
     *
     * @description 使用tag记录左右子树的被访问情况，仅当右子树被访问过时，访问根节点
     */
    private void postOrderNoRec2() {
        // 辅助栈
        Stack<BinaryTreeNode> nodeStack = new Stack<>();
        // 遍历指针
        BinaryTreeNode cur = root;
        while (!nodeStack.isEmpty() || cur != null) {
            // 遍历左子树直至末端
            while (cur != null) {
                // 将tag置左，表示左子树已访问
                cur.setTag(Constant.Tag.LEFT);
                nodeStack.push(cur);
                cur = cur.getLeftChild();
            }
            cur = nodeStack.pop();
            if (cur.getTag() == Constant.Tag.LEFT) {
                // 右子树未被访问，tag置右
                cur.setTag(Constant.Tag.RIGHT);
                // 根节点重新入栈
                nodeStack.push(cur);
                // 转入右子树
                cur = cur.getRightChild();
            } else {
                // 右子树已被访问，访问根
                System.out.println(cur.toString());
                /*
                 * 按照后序遍历 左右根 的顺序，根节点为最后访问的节点
                 * 所以此时当前节点所属的子树已全部访问
                 * 将当前节点置空，进入下次循环时直接弹栈访问其父节点
                 */
                cur = null;
            }
        }
    }


    /**
     * 中序遍历
     */
    public void inOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
        } else {
            System.out.println("----中序遍历(非递归)----");
            inOrderNoRec();
        }
    }


    /**
     * 中序遍历（递归）
     *
     * @param node 节点
     */
    private void inOrderRec(BinaryTreeNode node) {
        // 左孩不空，向左子树递归
        if (node.hasLeftChild()) {
            inOrderRec(node.getLeftChild());
        }
        System.out.println(node.toString());
        // 右孩不空，向右子树递归
        if (node.hasRightChild()) {
            inOrderRec(node.getRightChild());
        }
    }


    /**
     * 中序遍历（非递归）
     */
    private void inOrderNoRec() {
        Stack<BinaryTreeNode> nodeStack = new Stack<>();
        BinaryTreeNode p = root;
        while (!nodeStack.isEmpty() || p != null) {
            if (p != null) {
                // 只要左孩不空，就一直向左遍历，边遍历边入栈，直至左子树的左末端
                nodeStack.push(p);
                p = p.getLeftChild();
            } else {
                // p为空，说明左子树已遍历完，开始出栈
                p = nodeStack.pop();
                System.out.println(p.toString());
                // 根访问，之后进入右子树
                p = p.getRightChild();
            }
        }
    }


    /**
     * 根据编号查询
     *
     * @param no 节点编号
     * @return
     */
    public BinaryTreeNode searchByNo(int no) {
        return root == null ? null : root.postOrderSearch(no);
    }


    /**
     * 根据编号删除节点（及其子树）
     *
     * @param no 节点编号
     * @return true 删除成功； false 未能删除
     */
    public boolean deleteByNo(int no) {
        if (root == null) {
            // 树空
            return false;
        } else {
            // 判断根节点是否为目标节点
            if (root.getNo() == no) {
                root = null;
                return true;
            }
        }
        return root.deleteChildByNo(no);
    }
}
