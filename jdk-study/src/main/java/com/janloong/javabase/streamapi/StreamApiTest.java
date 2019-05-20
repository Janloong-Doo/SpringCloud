package com.janloong.javabase.streamapi;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * des: StreamApi测试类
 *
 * @author Janloong
 * @create 2017-06-17 上午11:53
 **/
public class StreamApiTest {

    public static void main(String[] args) {
        System.out.println("------测试一------");
        //测试一  遍历list
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Stream<Integer> stream = list.stream();
        stream.filter((x) -> {//过滤条件
            return x % 2 == 0;
        }).map((x) -> { //符合条件时执行
            return x * x;
        }).forEach(System.out::println); //逐条打印

        System.out.println("------------------华丽的分割线-----------------");

        //测试二
        class NaturalSupplier implements Supplier<Long> {
            long value = 0;

            public Long get() {
                this.value = this.value + 1;
                return this.value;
            }
        }
        Stream<Long> natural = Stream.generate(new NaturalSupplier());
        natural.map((x) -> {
            return x * x;
        }).limit(10).forEach(System.out::println);

        System.out.println("------------------华丽的分割线-----------------");

        //测试三
        class FibonacciSupplier implements Supplier<Long> {
            long a = 0;
            long b = 1;

            @Override
            public Long get() {
                long x = a + b;
                a = b;
                b = x;
                return a;
            }
        }
        Stream<Long> fibonacci = Stream.generate(new FibonacciSupplier());
        //执行10次
        fibonacci.limit(10).forEach(System.out::println);
        System.out.println("------------------华丽的分割线-----------------");
        //从第20次开始 执行10次
        Stream<Long> fLongStream = Stream.generate(new FibonacciSupplier());
        List<Long> longList = fLongStream.skip(20).limit(10).collect(Collectors.toList());
        longList.forEach(System.out::println);

        System.out.println("------------------华丽的分割线-----------------");
        //测试四
        //Map集合遍历
        int te = 12;
        Map<String, Integer> items = new HashMap();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
        items.forEach((k, v) -> {
            System.out.println(k + "--" + v);
        });
        //List集合遍历
        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("D");
        stringList.add("E");
        stringList.forEach(System.out::println);

    }


}
