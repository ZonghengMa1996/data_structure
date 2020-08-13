package com.learning.lesson14avl;

/**
 * 二叉排序树结点
 *
 * @author Zongheng Ma
 * @date 2020/8/5
 */
public class BalanceTreeNode {

    /**
     * 数据域
     */
    private Integer val;

    /**
     * 左子结点
     */
    private BalanceTreeNode left;

    /**
     * 右子结点
     */
    private BalanceTreeNode right;

    public BalanceTreeNode(int val) {
        this.val = val;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public BalanceTreeNode getLeft() {
        return left;
    }

    public void setLeft(BalanceTreeNode left) {
        this.left = left;
    }

    public BalanceTreeNode getRight() {
        return right;
    }

    public void setRight(BalanceTreeNode right) {
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
    public void add(BalanceTreeNode node) {
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

        // 右子树高度 - 左子树高度 > 1，左旋转
        if (rightHeight() - leftHeight() > 1) {
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                // 右子树的左子树高度大于右子树的右子树高度，先对右子树RR
                this.right.rightRotate();
            }
            leftRotate();
            /* 双旋转调整高度之后，左右子树高度变化，防止被右旋转的代码影响 */
            return;
        }
        // 左子树高度 - 右子树高度 > 1，右旋转
        if (leftHeight() - rightHeight() > 1) {
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                // 左子树的右子树高度大于左子树的左子树高度，先对左子树LR
                this.left.leftRotate();
            }
            rightRotate();
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
    public BalanceTreeNode findTarget(int val) {
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
    public BalanceTreeNode findParent(int val) {
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


    /**
     * 返回当前结点为根的子树的高度
     *
     * @return 以左子树、右子树深度的较大值（+1）为当前结点为根的子树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }


    /**
     * 获取当前结点左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        return left == null ? 0 : left.height();
    }


    /**
     * 获取当前结点右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        return right == null ? 0 : right.height();
    }


    /**
     * 左旋转
     */
    public void leftRotate() {
        // 创建一个新结点，值为当前结点的值
        BalanceTreeNode node = new BalanceTreeNode(this.val);
        /*
         * 新结点的左指针指向当前结点的左子树
         * 新结点的右指针指向当前结点的右子树
         */
        node.left = this.left;
        node.right = this.right.left;
        /*
         * 当前结点的值替换为右子节点的值
         * 当前结点的右指针指向右子树的右子树
         * 当前结点的左指针指向新结点
         */
        this.val = this.right.val;
        this.right = this.right.right;
        this.left = node;
    }


    /**
     * 右旋转
     */
    public void rightRotate() {
        // 创建一个新结点，值为当前结点的值
        BalanceTreeNode node = new BalanceTreeNode(this.val);
        /*
         * 新结点的右指针指向当前结点的右子树
         * 左指针指向当前结点的右子树的左子树
         */
        node.right = this.right;
        node.left = this.left.right;
        /*
         * 当前结点的值改为其左子节点的值
         * 当前结点的左指针指向其左子树的左子树
         * 当前结点的右指针指向新结点
         */
        this.val = this.left.val;
        this.left = this.left.left;
        this.right = node;
    }
}