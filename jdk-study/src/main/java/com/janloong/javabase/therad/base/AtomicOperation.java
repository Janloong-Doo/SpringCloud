/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AtomicOperation.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/8/2 下午2:30
 : LastModify: 2019/8/2 下午2:30
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.javabase.therad.base;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程原子操作运算
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-08-02 14:30
 */
public class AtomicOperation {
    private static final int THREADS_CONUT = 20;
    /**
     * 测试时需要注释，因为当声明该原子类时，相应的原子同步效果生效
     * <p>
     * jdk.internal.misc.Unsafe.getUnsafe();  使用了cas指令操作。 即非同步操作时的‘操作和冲突检测等一系列行为’
     */
    public static AtomicInteger count = new AtomicInteger(0);

    /**
     * 1、保证变量在线程间可见，对volatile变量所有的写操作都能立即反应到其他线程中，换句话说，volatile变量在各个线程中是一致的（得益于java内存模型—"先行发生原则"）；
     * <p>
     * 注意： 关键字能保证变量在所有线程中的可见性， 但不能保证在并发运算下是安全的。
     * <p>
     * 2、禁止指令的重排序优化；
     */
    public static volatile int countVolatile = 0;

    public static void increase() {
        System.out.println(countVolatile);
        countVolatile++;
    }

    //public static void increase2() {
    //    count.incrementAndGet();
    //}

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_CONUT];
        for (int i = 0; i < THREADS_CONUT; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
            System.out.println(countVolatile);
        }
    }

}
