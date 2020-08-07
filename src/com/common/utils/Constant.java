package com.common.utils;

/**
 * 常量
 *
 * @author Zongheng Ma
 * @date 2020-5-7
 */
public class Constant {

    /**
     * 线索化二叉树中指针的类型
     */
    public enum PointerType {
        // 指向子节点
        Child,
        // 指向线索（前驱或后继）
        Thread
    }

    /**
     * 后序遍历中子树访问情况标记
     */
    public enum Tag {
        // 左子树已被访问
        LEFT,
        // 右子树已被访问
        RIGHT
    }

    /**
     * ASCII码
     */
    public interface Ascii {
        // 0
        int ZERO = 48;

        // 9
        int NINE = 57;

        // . 小数点
        int POINT = 46;
    }

    /**
     * 哈夫曼树路径
     */
    public interface TreePath {
        // 左子树
        String LEFT = "0";

        // 右子树
        String RIGHT = "1";
    }

    /**
     * 加号
     */
    public static final String PLUS = "+";

    /**
     * 减号
     */
    public static final String MINUS = "-";

    /**
     * 乘号
     */
    public static final String MULTIPLY = "*";

    /**
     * 除号
     */
    public static final String DIVISION = "/";

    /**
     * 左括号
     */
    public static final String LEFT_BRACKET = "(";

    /**
     * 右括号
     */
    public static final String RIGHT_BRACKET = ")";
}
