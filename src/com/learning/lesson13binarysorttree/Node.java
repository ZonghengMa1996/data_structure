package com.learning.lesson13binarysorttree;

/**
 * 二叉排序树结点
 *
 * @author Zongheng Ma
 * @date 2020/8/5
 */
public class Node {

    /**
     * 数据域
     */
    private Integer val;

    /**
     * 左子结点
     */
    private Node left;

    /**
     * 右子结点
     */
    private Node right;

    public Node(int val) {
        this.val = val;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node[" +
                "val = " + val +
                ']';
    }


    /**
     * 二叉排序树的添加结点
     *
     * @param node 树结点
     */
    public void add(Node node) {
        if (node.val < this.val) {
            // 待添加结点的data小于当前结点，应添加至左子树
            if (this.left == null) {
                // 左子结点空，赋给左子节点
                this.left = node;
            } else {
                // 向左子树递归
                this.left.add(node);
            }
        } else {
            // 待添加节点的data大于等于当前节点
            if (this.right == null) {
                // 右子节点空，赋给右子节点
                this.right = node;
            } else {
                // 向右子树递归
                this.right.add(node);
            }
        }
    }


    /**
     * 中序遍历
     */
    public void inOrder() {
        if (this.left != null) {
            this.left.inOrder();
        }
        System.out.println(this.toString());
        if (this.right != null) {
            this.right.inOrder();
        }
    }


    /**
     * 找到待删除的目标结点
     *
     * @param val 目标结点的值
     * @return
     */
    public Node findTarget(int val) {
        if (this.val == val) {
            return this;
        }
        if (val < this.val) {
            // 目标值较小，向左子树递归
            return this.left == null ? null : this.left.findTarget(val);
        } else {
            // 目标值较大，向右子树递归
            return this.right == null ? null : this.right.findTarget(val);
        }
    }


    /**
     * 查找目标结点的父节点
     *
     * @param val 待删除结点的关键字
     * @return 待删除结点的父节点
     */
    public Node findParent(int val) {
        // 判断是否为左子结点或者右子节点
        boolean leftChild = this.left != null && this.left.val == val;
        boolean rightChild = this.right != null && this.right.val == val;
        if (leftChild || rightChild) {
            return this;
        } else {
            if (val < this.val) {
                // 目标值较小，向左子树递归
                return this.left == null ? null : this.left.findParent(val);
            } else {
                // 目标值较大，向右子树递归
                return this.right == null ? null : this.right.findParent(val);
            }
        }
    }
}
