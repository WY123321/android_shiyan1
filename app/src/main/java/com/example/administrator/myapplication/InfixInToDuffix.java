package com.example.administrator.myapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.*;
import java.util.ArrayList;
import java.util.*;

public class InfixInToDuffix {

    //使用集合定义运算优先级别
    private static final Map<Character,Integer>basic =new HashMap<Character, Integer>();
    static {
        basic.put('-',1);
        basic.put('+', 1);
        basic.put('×', 2);
        basic.put('÷', 2);
        basic.put('(', 0);//（）的优先级最高，为0
    }


    //将中缀表达式转换为后缀表达式
    public String toSuffix(StringBuilder infix){
        List<String> queue = new ArrayList<String>();             //队列  存储 数字  最后是  后缀表达式
        List<Character> stack = new ArrayList<Character>();            //栈    运算符  最后会被 弹空

        char[] charArr = infix.substring(0,infix.length()).trim().toCharArray();                //  用于拆分数字或符号
        String standard = "×÷+-()";                    //判定标准
        char ch = '*';
        int len = 0;             //用于记录字符长度
        for (int i = 0; i < charArr.length; i++) {

            ch = charArr[i];
            if(Character.isDigit(ch)) {                               //如果当前变量为 数字
                len++;
            }else if(ch == '.'){                                       //如果当前变量为  .  会出现在小数里面
                len++;
            }else if(standard.indexOf(ch) != -1) {                              //如果是上面标准中的 任意一个符号
                if(len > 0) {
                    queue.add(String.valueOf(Arrays.copyOfRange(charArr, i - len, i)));    //说明符号之前的可以截取下来做数字
                    len = 0;
                }
                if(ch == '(') {                                                            //如果是左括号
                    stack.add(ch);                                                        //将左括号 放入栈中
                    continue;                                                            //跳出本次循环  继续找下一个位置
                }
                if (!stack.isEmpty()) {                                                    //如果栈不为empty
                    int size = stack.size() - 1;                              //获取栈的大小-1  即代表栈最后一个元素的下标
                    boolean flag = false;                                                //设置标志位
                    while (size >= 0 && ch == ')' && stack.get(size) != '(') {       //若当前ch为右括号，则 栈里元素从栈顶一直弹出，直到弹出到 左括号
                        queue.add(String.valueOf(stack.remove(size)));
//注意此处条件：ch并未入栈，所以并未插入队列中；同样直到找到左括号的时候，循环结束了，所以左括号也不会放入队列中【也就是：后缀表达式中不会出现括号】
                        size--;          //size-- 保证下标永远在栈最后一个元素
                        flag = true;                                                    //设置标志位为true  表明一直在取（）中的元素
                    }
                    if(ch==')'&&stack.get(size) == '('){
                        flag = true;
                    }
                    while (size >= 0 && !flag && basic.get(stack.get(size)) >= basic.get(ch)) {
                        //若取得不是（）内的元素，并且当前栈顶元素的优先级>=对比元素 那就出栈插入队列
                        queue.add(String.valueOf(stack.remove(size)));   //同样  此处也是remove()方法，既能得到要获取的元素，也能将栈中元素移除掉
                        size--;
                    }
                }
                if(ch != ')') {                                                            //若当前元素不是右括号
                    stack.add(ch);                                                        //就要保证这个符号 入栈
                } else {                                                                //否则就要出栈 栈内符号
                    stack.remove(stack.size() - 1);
                }
            }
            if(i == charArr.length - 1) {                                                //如果已经走到了  中缀表达式的最后一位
                if(len > 0) {                                                            //如果len>0  就截取数字
                    queue.add(String.valueOf(Arrays.copyOfRange(charArr, i - len+1, i+1)));
                }
                int size = stack.size() - 1;
                while (size >= 0) {
                    //一直将栈内  符号全部出栈 并且加入队列中
                    queue.add(String.valueOf(stack.remove(size)));
                    size--;
                }
            }

        }
        String a = queue.toString();
        return a.substring(1,a.length()-1);
    }


    public String dealEquation(String equation){

        String [] arr = equation.split(", ");                                    //拆分字符串
        List<String> list = new ArrayList<String>();
        //存储运算过程的集合

        for (int i = 0; i < arr.length; i++) {
            // 因为list.remove的缘故，所以取出最后一个数个最后两个数  都是size-2
            int size = list.size();
            switch (arr[i]) {
                case "+": double a = Double.parseDouble(list.remove(size-2))+ Double.parseDouble(list.remove(size-2)); list.add(String.valueOf(a));
                break;
                case "-": double b = Double.parseDouble(list.remove(size-2))- Double.parseDouble(list.remove(size-2)); list.add(String.valueOf(b));
                break;
                case "×": double c = Double.parseDouble(list.remove(size-2))* Double.parseDouble(list.remove(size-2)); list.add(String.valueOf(c));
                break;
                case "÷": double d = Double.parseDouble(list.remove(size-2))/ Double.parseDouble(list.remove(size-2)); list.add(String.valueOf(d));
                break;
                default: list.add(arr[i]);     break;
                //如果是数字  直接放进list中
            }
        }

        return list.size()
                == 1 ? list.get(0) : "运算失败" ;            //最终list不是1个结果就是算错了
    }

}