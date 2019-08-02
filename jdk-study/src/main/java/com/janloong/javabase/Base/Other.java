/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: Ohter.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/8/2 下午5:06
 : LastModify: 2019/8/2 下午5:06
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.javabase.Base;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-08-02 17:06
 */
public class Other {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        for (; ; ) {
            System.out.println("停不停啊?");
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
