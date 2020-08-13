package com.learning.lesson14avl;

/**
 * 平衡二叉树
 *
 * @author Zongheng Ma
 * @date 2020/8/11
 */
public class BalanceTree {

    public static void main(String[] args) {
        int[] array = {22, 14, 10, 11, 7, 6, 8, 9, 3, 29};
        BalanceTree tree = new BalanceTree();
        for (int val : array) {
            tree.addNode(new BalanceTreeNode(val));
        }
        tree.inOrder();
        System.out.printf("左子树高度：%d\n", tree.getRoot().leftHeight());
        System.out.printf("右子树高度：%d\n", tree.getRoot().rightHeight());
    }


    /**
     * 根结点
     */
    private BalanceTreeNode root;

    public BalanceTreeNode getRoot() {
        return root;
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void addNode(BalanceTreeNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
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


    /**
     * 删除节点
     *
     * @param val 目标结点的关键字
     * @return 删除成功 1; 失败 -1
     */
    public void delNode(int val) {
        if (root == null) {
            System.out.println("树为空");
            return;
        }
        // 寻找目标结点
        BalanceTreeNode target = searchTarget(val);
        if (target == null) {
            System.out.printf("未找到关键字为 %d 的结点\n", val);
            return;
        }
        // 若根结点为待删除结点，且树只有根结点一个结点，直接删除
        if (root == target && root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }

        // 根据目标结点的子树情况进行删除
        BalanceTreeNode parent = searchParent(val);
        boolean hasLeft = target.getLeft() != null;
        boolean hasRight = target.getRight() != null;

        /*
         * 待删除节点为叶子节点的情况
         */
        if (!hasLeft && !hasRight) {
            if (parent.getLeft() != null && parent.getLeft().getVal() == val) {
                parent.setLeft(null);
            } else if (parent.getRight() != null && parent.getRight().getVal() == val) {
                parent.setRight(null);
            }
            return;
        }

        /*
         * 有且仅有一棵子树的情况
         * 需注意parent = null的情况，即target为根时
         * 若直接调用parent的setLeft方法会导致NullPointerException
         * 此时应将target的左/右子节点赋给root
         */
        if (hasLeft ^ hasRight) {
            if (hasLeft) {
                // target有左子结点
                if (parent == null) {
                    root = target.getLeft();
                } else {
                    if (parent.getLeft() != null && parent.getLeft().getVal() == val) {
                        parent.setLeft(target.getLeft());
                    } else if (parent.getRight() != null && parent.getRight().getVal() == val) {
                        parent.setRight(target.getLeft());
                    }
                }
            } else {
                // target有右子结点
                if (parent == null) {
                    root = target.getRight();
                } else {
                    if (parent.getLeft() != null && parent.getLeft().getVal() == val) {
                        parent.setLeft(target.getRight());
                    } else if (parent.getRight() != null && parent.getRight().getVal() == val) {
                        parent.setRight(target.getRight());
                    }
                }
            }
            return;
        }

        // 有两棵子树的情况，先找target右子树上关键字最小的结点
        BalanceTreeNode temp = target.getRight();
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        // 暂存其关键字
        int tempVal = temp.getVal();
        // 删除这个结点
        delNode(tempVal);
        // 将关键字赋给目标结点，也就是将目标结点移除，将temp挪至target位置
        target.setVal(tempVal);
    }


    /**
     * 寻找目标结点
     *
     * @param targetValue 待删除结点的关键字
     * @return 待删除结点
     */
    public BalanceTreeNode searchTarget(int targetValue) {
        if (root == null) {
            return null;
        } else {
            return root.findTarget(targetValue);
        }
    }


    /**
     * 寻找待删除结点的父节点
     *
     * @param targetValue 待删除结点的关键字
     * @return 待删除结点的父节点
     */
    public BalanceTreeNode searchParent(int targetValue) {
        if (root == null) {
            return null;
        } else {
            return root.findParent(targetValue);
        }
    }
}
