package com.janloong.javabase.streamapi.lambda;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-03-26 10:18
 */
public class User {
    private String name;
    private Integer age;
    private List<String> book = new ArrayList<>();

    //消费函数接口
    public static User create(Supplier<User> supplier) {
        return supplier.get();
    }

    //转换函数接口
    //public  void create2(Function<User, String> function) {
    //    //String apply = function.apply();
    //    //Function<User, String> function1 = new Function<>() {
    //    function = user1 -> {
    //        String name = user1.getName();
    //        return name;
    //    };
    //}


    //打印方法=========start===========
    public void print() {
        System.out.println("打印方法");
    }

    public static void print2(User user) {
        System.out.println("打印方法");
    }

    public void print3(final User user2) {
        System.out.println("打印方法");
    }
    //打印方法=========end===========

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getBook() {
        return book;
    }

    public void addBook(String book) {
        this.book.add(book);
    }

    public void setBook(List<String> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", book=" + book +
                '}';
    }
}
