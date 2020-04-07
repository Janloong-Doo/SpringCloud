/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AggregatedService.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/8/1 下午2:20
 : LastModify: 2019/8/1 下午2:20
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.thread;


import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 聚合子业务测试
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-08-01 14:20
 */
@Service
public class AggregatedService {

    public long test1(String name) {
        System.out.println("test1的线程  " + Thread.currentThread().getName());
        try {
            int timeout = 10;
            TimeUnit.SECONDS.sleep(timeout);
            System.out.println("test1===睡眠:" + timeout + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 520;
    }

    public long test2(String name) {
        System.out.println("test2的线程  " + Thread.currentThread().getName());
        try {
            int timeout = 10;
            TimeUnit.SECONDS.sleep(timeout);
            System.out.println("test2===睡眠:" + timeout + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 618;
    }

    public long test3(String name) {
        System.out.println("test3的线程  " + Thread.currentThread().getName());
        try {
            int timeout = 10;
            TimeUnit.SECONDS.sleep(timeout);
            System.out.println("test3==睡眠:" + timeout + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 6664;
    }

    public long test4(String name) {
        System.out.println("test4的线程  " + Thread.currentThread().getName());
        try {
            int timeout = 10;
            TimeUnit.SECONDS.sleep(timeout);
            System.out.println("test4===睡眠:" + timeout + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 123;
    }

    public long test5(String name) {
        System.out.println("test5的线程  " + Thread.currentThread().getName());
        try {
            int timeout = 4;
            TimeUnit.SECONDS.sleep(timeout);
            System.out.println("test5===睡眠:" + timeout + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 99;
    }

    public long test6(String name) {
        System.out.println("test6的线程  " + Thread.currentThread().getName());
        try {
            int timeout = 8;
            TimeUnit.SECONDS.sleep(timeout);
            System.out.println("test6===睡眠:" + timeout + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 66;
    }
}
