package com.janloong.javabase.Enum;

/**
 * @author Janloong
 * @create 2017-07-12 下午6:46
 **/
public class EnumTest {
    public static void main(String[] args) {
        //EnumUtils
        EnumUtils a = EnumUtils.valueOf("A");
        System.out.println(a.getValue());

        //Operation
        System.out.println("6 + 2 = " + Operation.PLUS.calculate(6, 3));
        System.out.println("6 - 2 = " + Operation.MINUS.calculate(6, 2));
        System.out.println("6 * 2 = " + Operation.TIMES.calculate(6, 2));
        System.out.println("6 / 2 = " + Operation.DIVIDE.calculate(6, 2));
    }
}
