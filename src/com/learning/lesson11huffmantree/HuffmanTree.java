package com.learning.lesson11huffmantree;

import com.common.utils.Constant;

import java.util.*;

/**
 * 哈夫曼树
 *
 * @author Zongheng Ma
 * @date 2020-7-24
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        // 获取哈夫曼树的根
        HuffmanTreeNode root = createHuffTree(arrToList(array));
        // 先序遍历此哈夫曼树
        preOrder(root);
    }


    /**
     * 权值数组转为哈夫曼树
     *
     * @param arr 权值的数组
     * @return 结点list
     */
    public static List<HuffmanTreeNode> arrToList(int[] arr) {
        List<HuffmanTreeNode> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new HuffmanTreeNode(value));
        }
        return nodes;
    }


    /**
     * 创建哈夫曼树
     *
     * @param nodes 结点list
     * @return 创建的哈夫曼树的根
     */
    public static HuffmanTreeNode createHuffTree(List<HuffmanTreeNode> nodes) {

        /* 步骤如下：
         * （1）对序列进行排序（升序）
         * （2）从结点序列中取出头两个结点（权最小的两个）
         * （3）两个结点权相加作为新节点的权，新节点加入序列中
         * （4）重复（1）（2）（3）直至nodes中仅剩一个结点，即序列中的元素都已加入到哈夫曼树中
         */
        while (nodes.size() > 1) {
            // 排序（从小到大）
            Collections.sort(nodes);

            // 取出权最小的两个结点，权之和赋给新结点
            HuffmanTreeNode left = nodes.get(0);
            HuffmanTreeNode right = nodes.get(1);
            HuffmanTreeNode parent = new HuffmanTreeNode(left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            // 将用到的两个子节点去除
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }

        // 最后nodes中仅剩的结点即为哈夫曼树的根
        return nodes.get(0);
    }


    /**
     * 先序遍历
     *
     * @param root 根结点
     */
    public static void preOrder(HuffmanTreeNode root) {
        if (root == null) {
            System.out.println("树为空");
        } else {
            root.preOrder();
        }
    }


    /**
     * 字符与编码的映射
     */
    public static Map<Byte, String> codeMap = new HashMap<>(32);


    /**
     * 重载方法，根据根结点获取哈夫曼树的编码表
     *
     * @param root 根结点
     * @return 字符与编码的映射
     */
    public static Map<Byte, String> getCodingTable(HuffmanTreeNode root) {
        // 从根节点开始，递归遍历哈夫曼树，将叶子结点的哈夫曼编码存入映射
        getCodingTable(root, "", new StringBuilder());
        return codeMap;
    }


    /**
     * 获取节点的哈夫曼编码并存入映射
     *
     * @param node     当前结点
     * @param path     路径（向左 0；向右 1）
     * @param lastCode 父结点的编码
     */
    private static void getCodingTable(HuffmanTreeNode node, String path, StringBuilder lastCode) {
        if (node != null) {
            // 父结点的编码 + 路径 = 当前结点的哈夫曼编码
            StringBuilder curCode = new StringBuilder(lastCode);
            curCode.append(path);
            if (node.getData() == null) {
                // 非叶子结点，向左向右递归
                getCodingTable(node.getLeft(), Constant.TreePath.LEFT, curCode);
                getCodingTable(node.getRight(), Constant.TreePath.RIGHT, curCode);
            } else {
                // 叶子结点，data域作为键，编码作为value存入映射，回溯
                codeMap.put(node.getData(), curCode.toString());
            }
        }
    }
}
