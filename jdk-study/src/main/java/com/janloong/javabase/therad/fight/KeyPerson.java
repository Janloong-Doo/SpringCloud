package com.janloong.javabase.therad.fight;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-02-25 14:10
 */
public class KeyPerson extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始了战斗");

        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"左突右杀，攻击隋军");
        }

        System.out.println(Thread.currentThread().getName()+"结束了战斗");

    }
}
