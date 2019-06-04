package com.janloong.javabase.streamapi.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //customUsage();

        //一元二元操作
        //unaryOpera();

        //集合收集器
        //collectorsTest();

        //flatmap处理
        flatMapTest();
    }

    private static void flatMapTest() {

        String[] words = {"Hello", "World"};
        Stream<String[]> stream = Stream.of(words).map(s -> s.split(""));
        stream.forEach(s -> {
            List<String> list = Arrays.asList(s);
            System.out.println(list);
        });

        List<String> collect = Stream.of(words).        //数组转换流
                map(w -> w.split("")).  //去掉“”并获取到两个String[]
                flatMap(Arrays::stream).        //方法调用将两个String[]扁平化为一个stream
                distinct().                     //去重
                collect(Collectors.toList());
        //collect = [H, e, l, o, W, r, d]
        System.out.println("collect = " + collect);
    }

    private static void collectorsTest() {
        System.out.println("集合收集器测试========start=======");
        User user = User.create(User::new);
        user.setName("doo1");
        user.setAge(26);
        user.addBook("chinese");
        user.addBook("english");

        User user2 = User.create(User::new);
        user2.setName("doo2");
        user2.setAge(18);
        user2.addBook("chinese");
        user2.addBook("english");
        user2.addBook("java");

        Stream<User> userStream = Stream.of(user, user2);
        User user1 = userStream.collect(Collectors.minBy(Comparator.comparing(User::getAge))).get();
        //stream 执行一次后就会关闭
        //User user2 = userStream.collect(Collectors.maxBy(Comparator.comparing(User::getAge))).get();
        List<User> users = Arrays.asList(user, user2);
        User user4 = users.stream().max(Comparator.comparing(o -> o.getBook().size())).get();
        Double collect = users.stream().collect(Collectors.averagingInt(User::getAge));
        Map<Boolean, List<User>> java = users.stream().collect(Collectors.partitioningBy(o -> o.getBook().contains("java")));
        Map<Integer, List<User>> collect1 = users.stream().collect(Collectors.groupingBy(User::getAge));
        String com = users.stream().map(User::getName).collect(Collectors.joining("-", "@", "com"));
        System.out.println(user1);
        System.out.println(user4);
        System.out.println(collect);
        System.out.println(java);
        System.out.println(collect1);
        System.out.println(com);
        System.out.println("集合收集器测试========end=======");

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
