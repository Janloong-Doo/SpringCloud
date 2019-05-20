package com.janloong.javabase.lambda;


import java.util.function.Supplier;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-26 10:18
 */
public class User {


    public static User create(Supplier<User> supplier) {
        return supplier.get();
    }

    public void print() {
        System.out.println("打印方法");
    }

    public static void print2(User user) {
        System.out.println("打印方法");
    }

    public void print3(final User user2) {
        System.out.println("打印方法");
    }
}
