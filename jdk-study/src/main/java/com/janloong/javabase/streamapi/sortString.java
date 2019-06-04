/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: sortString.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-4 下午2:35
 : LastModify: 18-12-5 上午11:27
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.javabase.streamapi;

import java.util.*;
import java.util.stream.Stream;

/**
 * des: 字典排序问题
 *
 * @author Janloong
 * @create 2017-07-12 下午4:29
 **/
public class sortString {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "a");
        map.put("c", "c");
        map.put("f", "f");
        map.put("1", "1");
        map.put("d", "d");

        Set<Map.Entry<String, String>> strings = map.entrySet();
        List list = new ArrayList<Map.Entry<String, String>>(strings);

        Stream<Map.Entry<String, String>> stream = list.stream();
        stream.forEach(System.out::println);

        //普通写法
        //Collections.sort(list, new Comparator<Map.Entry<String,String>>() {
        //    @Override
        //    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
        //        return o1.getKey().compareToIgnoreCase(o2.getKey());
        //    }
        //});

        //lambda 表达式
        Collections.sort(list, (Comparator<Map.Entry<String, String>>) (o1, o2) -> o1.getKey().compareToIgnoreCase
                (o2.getKey()));

        Map<String,String> map1=new HashMap<>();
        Stream<Map.Entry<String,String>> s = list.stream();
        //s.forEach(System.out::println);
        s.forEach((k) -> {
            System.out.println(k.getKey()+"---"+k.getValue());
            map1.put(k.getKey(), k.getValue());
        });

        map1.forEach((k,v) -> {
            System.out.println(k+"===="+v);
        });

    }
}
