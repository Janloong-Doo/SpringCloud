package com.janloong.javabase.therad.fight;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-02-25 13:26
 */
public class ArmyRunable implements Runnable {
    //volatile保证线程可以正常读取其他线程写入的值
    //可见性 ref JMM , happens-before 原则
    volatile boolean isFight = true;
    // boolean isFight = true;

    @Override
    public void run() {
        while (isFight) {
            for (int i = 0; i < 5; i++) {

                System.out.println(Thread.currentThread().getName() + "第[" + i + "]次进攻");
                //释放线程资源，下次重新争夺线程资源
                Thread.yield();
            }
        }

        System.out.println(Thread.currentThread().getName()+"结束战斗");
    }
}
