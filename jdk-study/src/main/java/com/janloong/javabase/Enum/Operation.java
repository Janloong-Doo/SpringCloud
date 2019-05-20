package com.janloong.javabase.Enum;

/**
 * @author Janloong
 * @create 2017-07-19 下午5:57
 **/
public enum Operation {

    // 用于执行加法运算
    PLUS { // 花括号部分其实是一个匿名内部子类

        @Override
        public double calculate(double x, double y) {
            return x + y;
        }

    },

    // 用于执行减法运算
    MINUS { // 花括号部分其实是一个匿名内部子类

        @Override
        public double calculate(double x, double y) {
            // TODO Auto-generated method stub
            return x - y;
        }

    },

    // 用于执行乘法运算
    TIMES { // 花括号部分其实是一个匿名内部子类

        @Override
        public double calculate(double x, double y) {
            return x * y;
        }

    },

    // 用于执行除法运算
    DIVIDE { // 花括号部分其实是一个匿名内部子类

        @Override
        public double calculate(double x, double y) {
            return x / y;
        }

    };

    //为该枚举类定义一个抽象方法，枚举类中所有的枚举值都必须实现这个方法
    public abstract double calculate(double x, double y);

}

