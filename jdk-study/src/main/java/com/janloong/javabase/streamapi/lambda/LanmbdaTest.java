package com.janloong.javabase.streamapi.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * des: lambda表达式测试
 *
 * @author Janloong
 * @version jdk1.8
 * @create 2017-06-17 上午11:45
 **/
public class LanmbdaTest {

    public static void main(String[] args) {
        //自定义使用
        customUsage();

        //一元二元操作
        unaryOpera();

        //

    }

    private static void customUsage() {
        User user = User.create(User::new);
        List<User> users = Arrays.asList(user);
        users.forEach(User::print);
        users.forEach(User::print2);
        users.forEach(user::print3);
        
    }

    private static void unaryOpera() {
        //UnaryOperator 一元操作
        UnaryOperator<Boolean> unaryOperator = doo -> !doo;
        Boolean apply = unaryOperator.apply(true);
        System.out.println(apply);
        UnaryOperator<String> stringUnaryOperator = String::toUpperCase;
        String result = stringUnaryOperator.apply("Janloong Doo");
        System.out.println(result);

        //BinaryOperator 二元操作
        BinaryOperator<String> binaryOperator = (x, y) -> x + y;
        String apply1 = binaryOperator.apply("Janloong", "Doo");
        System.out.println(apply1);
        BinaryOperator<Object> binaryOperator2 = (x, y) -> String.valueOf(x) + y;
        Object apply2 = binaryOperator2.apply("Janloong", "Doo");
        System.out.println(apply2);
    }


}
