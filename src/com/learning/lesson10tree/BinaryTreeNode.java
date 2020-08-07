package com.learning.lesson10tree;

import com.common.utils.Constant;

/**
 * 二叉树节点
 *
 * @author Zongheng Ma
 * @date 2020-6-29
 */
public class BinaryTreeNode {

    /**
     * 节点编号
     */
    private Integer no;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 左子节点
     */
    private BinaryTreeNode leftChild;

    /**
     * 右子节点
     */
    private BinaryTreeNode rightChild;

    /**
     * 记录左右子树被访问的情况
     */
    private Constant.Tag tag;

    /**
     * 左线索标记(true 前驱节点；false 左子节点)
     */
    public Constant.PointerType left;

    /**
     * 右线索标记(true 后继节点；false 右子节点)
     */
    public Constant.PointerType right;

    public BinaryTreeNode(Integer no, String name) {
        this.no = no;
        this.name = name;
        left = Constant.PointerType.Child;
        right = Constant.PointerType.Child;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public Constant.Tag getTag() {
        return tag;
    }

    public void setTag(Constant.Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    /**
     * 当前节点是否有左子节点
     *
     * @return true:有 false:无
     */
    public boolean hasLeftChild() {
        return getLeftChild() != null;
    }


    /**
     * 当前节点是否有右子节点
     *
     * @return true:有 false:无
     */
    public boolean hasRightChild() {
        return getRightChild() != null;
    }


    /**
     * 先序查找
     *
     * @param no 查找的编号
     * @return 匹配的节点
     * @description 根->左->右
     */
    public BinaryTreeNode preOrderSearch(int no) {
        // 先与当前节点比较
        if (getNo() == no) {
            return this;
        }
        BinaryTreeNode res = null;
        // 有左孩则向左递归
        if (hasLeftChild()) {
            res = getLeftChild().preOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        // 有右孩则向右递归
        if (hasRightChild()) {
            res = getRightChild().preOrderSearch(no);
        }
        return res;
    }


    /**
     * 中序查找
     *
     * @param no 编号
     * @return 匹配的节点
     * @description 左->根->右
     */
    public BinaryTreeNode inOrderSearch(int no) {
        BinaryTreeNode res = null;
        // 向左递归
        if (hasLeftChild()) {
            res = getLeftChild().inOrderSearch(no);
            if (res != null) {
                return res;
            }
        }
        // 与当前节点比较
        if (getNo() == no) {
            return this;
        }
        // 向右递归
        if (hasRightChild()) {
            res = getRightChild().inOrderSearch(no);
        }
        return res;
    }


    /**
     * 后序查找
     *
     * @param no 编号
     * @return 匹配的节点
     * @description 左->右->根
     */
    public BinaryTreeNode postOrderSearch(int no) {
        BinaryTreeNode res = null;
        // 向左递归
        if (hasLeftChild()) {
            res = getLeftChild().postOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        if (hasRightChild()) {
            res = getRightChild().postOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        return getNo() == no ? this : null;
    }


    /**
     * 根据编号删除节点
     *
     * @param no 编号
     * @return true 删除成功；false 未能删除
     */
    public boolean deleteChildByNo(int no) {
        // 若有左子节点
        if (hasLeftChild()) {
            if (getLeftChild().getNo() == no) {
                setLeftChild(null);
                return true;
            } else {
                // 左子节点不为目标节点，向左递归
                if (getLeftChild().deleteChildByNo(no)) {
                    return true;
                }
            }
        }
        // 若有右子节点
        if (hasRightChild()) {
            if (getRightChild().getNo() == no) {
                setRightChild(null);
                return true;
            } else {
                // 右子节点不为目标节点，向右递归
                return getRightChild().deleteChildByNo(no);
            }
        }
        // 左右都已遍历，未找到目标节点
        return false;
    }
}
