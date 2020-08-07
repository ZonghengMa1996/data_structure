package com.learning.lesson07stackcaculator;

import com.common.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式转后缀表达式
 *
 * @author Zongheng Ma
 * @date 2020-5-9
 */
public class InfixToSuffixDemo {

    public static void main(String[] args) {
        // 中缀表达式
        String infix = "110.1-(10.5*10+0.1)+0.5";
        // 转换后的后缀表达式
        String suffix = transferToSuffix(infix);
        System.out.printf("%s 对应的后缀表达式为：%s\n", infix, suffix);
        System.out.printf("%s 转换后的计算结果为：%s\n", infix, suffixCalculate(suffix));
    }


    /**
     * 小数或者整数的正则
     */
    private static final String REGX = "^(([^0][0-9]+|0)\\.([0-9]{1,2})$)|^(([^0][0-9]+|0)$)|^(([1-9]+)\\.([0-9]{1,2})$)|^(([1-9]+)$)";

    /**
     * 将中缀转为后缀表达式
     *
     * @param infix 输入的中缀表达式
     * @return 转换后的后缀表达式
     */
    public static String transferToSuffix(String infix) {
        // 表达式转为ArrayList
        List<String> list = toInfixList(infix.replace(" ", ""));
        // 符号栈
        Stack<String> opStack = new Stack<>();
        /*
         结果栈此处可以使用FIFO的结构（如ArrayList）进行处理
         使最后的顺序输出即为后缀表达式的实际顺序
         */
        List<String> resStack = new ArrayList<>();

        // 遍历表达式的list
        for (String item : list) {
            // 数字，压入结果栈
            if (item.matches(REGX)) {
                resStack.add(item);
                continue;
            }

            // 括号
            if (Constant.LEFT_BRACKET.equals(item)) {
                // 左括号直接入栈
                opStack.push(item);
                continue;
            }
            if (Constant.RIGHT_BRACKET.equals(item)) {
                // 右括号，匹配到左括号之前，将符号栈元素出栈并压入结果栈
                while (!opStack.peek().equals(Constant.LEFT_BRACKET)) {
                    resStack.add(opStack.pop());
                }
                // 左括号出栈（至此消除了一对括号）
                opStack.pop();
                continue;
            }

            // 运算符
            if (OpStack.isOp(item)) {
                // 符号栈空或者栈顶为左括号，直接入栈
                if (opStack.isEmpty() || opStack.peek().equals(Constant.LEFT_BRACKET)) {
                    opStack.push(item);
                    continue;
                }

                do {
                    if (InfixExpressionDemo.getPriority(item) <= InfixExpressionDemo.getPriority(opStack.peek())) {
                        // 若当前符号优先级小于符号栈栈顶，将栈顶出栈压入结果栈
                        resStack.add(opStack.pop());
                    } else {
                        break;
                    }
                } while (!opStack.isEmpty() && !opStack.peek().equals(Constant.LEFT_BRACKET));
                opStack.push(item);
            }
        }

        // 符号栈剩余元素出栈并压入结果栈
        while (!opStack.isEmpty()) {
            resStack.add(opStack.pop());
        }

        // 结果栈中元素按顺序输出即为对应的后缀表达式
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < resStack.size(); i++) {
            result.append(resStack.get(i)).append(i + 1 == resStack.size() ? "" : " ");
        }
        return result.toString();
    }


    /**
     * 中缀表达式转成对应list
     *
     * @param s 输入的中缀表达式
     * @return 对应的list
     */
    public static List<String> toInfixList(String s) {
        // 存放字符串的集合
        List<String> list = new ArrayList<>();
        // 遍历字符串的索引
        int i = 0;
        // 拼接多位数字
        StringBuilder str;
        // 存放扫描到的字符
        char c;

        do {
            if ((c = s.charAt(i)) < Constant.Ascii.ZERO || (c = s.charAt(i)) > Constant.Ascii.NINE) {
                // 运算符直接加入集合
                list.add("" + c);
                i++;
            } else {
                str = new StringBuilder();
                // 拼接多位数、小数
                while (i < s.length() && (c = s.charAt(i)) >= Constant.Ascii.ZERO && c <= Constant.Ascii.NINE) {
                    str.append(c);
                    i++;
                    // 下一字符为小数点，继续拼接
                    if (i < s.length() && (c = s.charAt(i)) == Constant.Ascii.POINT) {
                        str.append(c);
                        i++;
                    }
                }
                list.add(str.toString());
            }
        } while (i < s.length());
        return list;
    }


    /**
     * 根据后缀表达式求值
     *
     * @param exp 输入的逆波兰表达式
     * @return 运算结果
     */
    public static double suffixCalculate(String exp) {
        // 创建数栈
        Stack<Double> numStack = new Stack<>();

        // 将表达式按空格拆分并转为list
        List<String> expArray = SuffixExpressionDemo.stringToList(exp);
        // 遍历表达式
        for (String item : expArray) {
            if (item.matches(REGX)) {
                // 扫描到数字直接入数栈
                numStack.push(Double.parseDouble(item));
            } else {
                // 扫描到符号从数栈取数并运算
                double res = calculate(numStack.pop(), numStack.pop(), item);
                // 结果入数栈
                numStack.push(res);
            }
        }

        // 数栈内最后的元素即计算式的运算结果
        return numStack.pop();
    }


    /**
     * 计算
     *
     * @param rear  后一个数
     * @param front 前一个数
     * @param op    运算符
     * @return 运算结果
     * @throws RuntimeException
     */
    public static double calculate(double rear, double front, String op) {
        // 根据栈FILO的特性，后取出的操作数在前，先取出的操作数在后
        switch (op) {
            case "+":
                return front + rear;
            case "-":
                return front - rear;
            case "*":
                return front * rear;
            case "/":
                return front / rear;
            default:
                throw new RuntimeException("运算符有误");
        }
    }
}
