/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: fanxing.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-26 下午10:52
 : LastModify: 19-5-26 下午10:52
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.javabase.Base;


import java.util.ArrayList;
import java.util.List;

/**
 * 通配符  泛型
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-26 22:52
 */
public class fanxing {

    public static void main(String[] args) {

        //extends 可用于的返回类型限定，不能用于参数类型限定。
        //super 可用于参数类型限定，不能用于返回类型限定。
    }

    static void extendsDemo() {
        List<? extends Fruit> flist = new ArrayList<Apple>();
        //List<? extends Fruit> flist = new ArrayList<>();
        //List<Apple> flist = new ArrayList<Apple>();

        //complie error:
        //flist.add(new Apple());
        //flist.add(new Fruit());
        //flist.add(new Object());
        //flist.add(null); // only work for null

        Fruit fruit = flist.get(0);
        //Apple apple = (Apple)flist.get(0);
        //RedApple apple = (RedApple)flist.get(0);
    }

    static void superDemo() {
        List<? super Fruit> flist = new ArrayList<Fruit>();
        flist.add(new Fruit());
        flist.add(new Apple());
        flist.add(new RedApple());

        //compile error:
        //List<? super Fruit> flist = new ArrayList<Apple>();
        //Fruit item = flist.get(0);
    }

    static class Food {
        String name;
    }

    static class Fruit extends Food {
    }

    static class Apple extends Fruit {

    }

    static class RedApple extends Apple {
    }
}
