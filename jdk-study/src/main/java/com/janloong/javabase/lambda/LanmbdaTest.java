package com.janloong.javabase.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * des: lambda表达式测试
 *
 * @author Janloong
 * @version jdk1.8
 * @create 2017-06-17 上午11:45
 **/
public class LanmbdaTest {

    public static void main(String[] args) {
        User user = User.create(User::new);
        List<User> users = Arrays.asList(user);
        users.forEach(User::print);
        users.forEach(User::print2);
        users.forEach(user::print3);
    }


}
