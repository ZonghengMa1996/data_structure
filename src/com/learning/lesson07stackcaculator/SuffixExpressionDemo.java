package com.learning.lesson07stackcaculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器（栈处理后缀表达式）
 *
 * @author Zongheng Ma
 * @date 2020-5-9
 */
public class SuffixExpressionDemo {

    public static void main(String[] args) {

        // 为方便处理，数字与符号之间用空格隔开
        // 34 * 5 - 6
        String exp1 = "34 5 * 6 -";
        System.out.printf("%s = %d\n", exp1, calculate(exp1));

        // 4 * 5 - 8 + 60 + 8 / 2
        String exp2 = "4 5 * 8 - 60 + 8 2 / +";
        System.out.printf("%s = %d\n", exp2, calculate(exp2));
    }


    /**
     * 表达式转为ArrayList
     *
     * @param exp 输入的计算式
     * @return List
     */
    public static List<String> stringToList(String exp) {
        String[] array = exp.split(" ");
        // 使用Arrays.asList转数组为集合，返回的对象大小固定、无法进行添加删除操作；
        // 正常方法是 1、遍历数组然后add至一个集合； 2、new ArrayList(Arrays.asList(a))
        return new ArrayList<>(Arrays.asList(array));
    }


    /**
     * 根据后缀表达式求值
     *
     * @param exp 输入的逆波兰表达式
     * @return 运算结果
     */
    public static int calculate(String exp) {
        // 创建数栈
        Stack<Integer> numStack = new Stack<>();

        // 将表达式按空格拆分并转为list
        List<String> expArray = stringToList(exp);
        // 遍历表达式
        for (String item : expArray) {
            if (item.matches("\\d+")) {
                // 扫描到数字直接入数栈
                numStack.push(Integer.parseInt(item));
            } else {
                // 扫描到符号从数栈取数并运算
                int res = InfixExpressionDemo.calculate(numStack.pop(), numStack.pop(), item);
                // 结果入数栈
                numStack.push(res);
            }
        }

        // 数栈内最后的元素即计算式的运算结果
        return numStack.pop();
    }
}
