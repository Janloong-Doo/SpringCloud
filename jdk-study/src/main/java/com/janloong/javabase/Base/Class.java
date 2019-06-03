/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: Class.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-22 下午3:39
 : LastModify: 19-5-22 下午3:39
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.javabase.Base;


import java.lang.reflect.Constructor;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-22 15:39
 */
public class Class {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            java.lang.Class<?> aClass = classLoader.loadClass("com.janloong.javabase.entity.User");
            Constructor<?>[] constructors = aClass.getDeclaredConstructors();
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, String.class);
            Constructor<?> declaredConstructor2 = aClass.getDeclaredConstructor(String.class, String.class, Integer.class);
            System.out.println("1");
        } catch (Exception e) {
            System.out.println("错误：" + e.getMessage());
        }
    }
}
