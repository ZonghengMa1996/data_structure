package com.learning.lesson07stackcaculator;

import com.common.utils.Constant;
import com.learning.lesson06stack.ArrayStack;

/**
 * 栈处理计算式(中缀表达式)
 *
 * @author Zongheng Ma
 * @date 2020-5-7
 */
public class InfixExpressionDemo {

    public static void main(String[] args) {

        // 中缀表达式
        String infixExp = "1-(10+2)*2";
        // 输出结果
        System.out.printf("%s = %d\n", infixExp, infixCalculate(infixExp));
    }


    /**
     * 计算中缀表达式
     *
     * @param expression 输入的中缀表达式
     * @return 运算结果
     */
    public static int infixCalculate(String expression) {
        // 数栈
        ArrayStack numStack = new ArrayStack(20);
        // 符号栈
        OpStack opStack = new OpStack(20);
        // 扫描计算式的索引
        int index = 0;
        // 暂存扫描的字符
        String scanner;
        // 上个字符为符号
        boolean opFlag = true;

        // 扫描计算式
        while (index + 1 <= expression.length()) {
            scanner = expression.substring(index, index + 1);
            // 扫描到数字，直接入数栈
            if (!OpStack.isOp(scanner)) {
                // 判断上个字符是否为符号
                if (opFlag) {
                    numStack.push(Integer.parseInt(scanner));
                } else {
                    // 数栈的栈顶为当前数字的高位
                    int pre = numStack.pop();
                    numStack.push(pre * 10 + Integer.parseInt(scanner));
                }
                opFlag = false;
                index++;
                continue;
            }

            // 扫描到左括号，直接入栈
            if (Constant.LEFT_BRACKET.equals(scanner)) {
                opStack.push(scanner);
                opFlag = true;
                index++;
                continue;
            }
            // 扫描到右括号，栈内符号出栈并运算直至匹配到左括号
            if (Constant.RIGHT_BRACKET.equals(scanner)) {
                while (!Constant.LEFT_BRACKET.equals(opStack.getTop())) {
                    int res = calculate(numStack.pop(), numStack.pop(), opStack.pop());
                    // 结果入数栈
                    numStack.push(res);
                }
                // 舍弃这对括号
                opStack.pop();
                index++;
                continue;
            }

            // 扫描到运算符
            opFlag = true;
            // 判断符号栈是否空
            while (!opStack.isEmpty()) {
                // 比较当前符号与栈顶符号的优先级
                if (getPriority(scanner) <= getPriority(opStack.getTop())) {
                    // 栈内为同级或高级运算符，则先行执行栈内符号的运算
                    int res = calculate(numStack.pop(), numStack.pop(), opStack.pop());
                    // 结果入数栈
                    numStack.push(res);
                } else {
                    // 栈顶为低级运算符，退出循环
                    break;
                }
            }
            opStack.push(scanner);
            index++;
        }

        // 符号栈内剩余元素出栈并进行运算
        while (!opStack.isEmpty()) {
            int res = calculate(numStack.pop(), numStack.pop(), opStack.pop());
            numStack.push(res);
        }
        return numStack.pop();
    }


    /**
     * 获取运算符的优先级
     *
     * @param op
     * @return 返回数字越大优先级越高
     */
    public static int getPriority(String op) {
        // 加减优先级为1
        if (Constant.PLUS.equals(op) || Constant.MINUS.equals(op)) {
            return 1;
        }
        // 乘除优先级为2
        if (Constant.DIVISION.equals(op) || Constant.MULTIPLY.equals(op)) {
            return 2;
        }
        // 其他为-1
        return -1;
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
    public static int calculate(int rear, int front, String op) {
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
