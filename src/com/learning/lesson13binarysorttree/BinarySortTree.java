package com.learning.lesson13binarysorttree;

/**
 * 二叉排序树
 *
 * @author Zongheng Ma
 * @date 2020/8/5
 */
public class BinarySortTree {

    public static void main(String[] args) {
        // 用数组创建初始化二叉排序树
        int[] array = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree(null);
        for (int val : array) {
            binarySortTree.addNode(new Node(val));
        }
        // 中序遍历
        binarySortTree.inOrder();

        // 删除子结点
        binarySortTree.delNode(3);
        binarySortTree.delNode(5);
        binarySortTree.delNode(7);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(10);
        binarySortTree.inOrder();
        binarySortTree.delNode(1);
    }


    /**
     * 根结点
     */
    private Node root;

    public BinarySortTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void addNode(Node node) {
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
        Node target = searchTarget(val);
        if (target == null) {
            System.out.printf("未找到关键字为 %d 的结点\n", val);
            return;
        }
        // 若根结点为待删除结点，且树只有根结点一个结点，直接删除
        if (root == target && root.getLeft() == null && root.getRight() == null) {
            root = null;
            System.out.printf("关键字为 %d 的结点已被删除\n", val);
            return;
        }

        // 根据目标结点的子树情况进行删除
        Node parent = searchParent(val);
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
            System.out.printf("关键字为 %d 的结点已被删除\n", val);
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
            System.out.printf("关键字为 %d 的结点已被删除\n", val);
            return;
        }

        // 有两棵子树的情况，先找target右子树上关键字最小的结点
        Node temp = target.getRight();
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        // 暂存其关键字
        int tempVal = temp.getVal();
        // 删除这个结点
        delNode(tempVal);
        // 将关键字赋给目标结点，也就是将目标结点移除，将temp挪至target位置
        target.setVal(tempVal);
        System.out.printf("关键字为 %d 的结点已被删除\n", val);
    }


    /**
     * 寻找目标结点
     *
     * @param targetValue 待删除结点的关键字
     * @return 待删除结点
     */
    public Node searchTarget(int targetValue) {
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
    public Node searchParent(int targetValue) {
        if (root == null) {
            return null;
        } else {
            return root.findParent(targetValue);
        }
    }
}
