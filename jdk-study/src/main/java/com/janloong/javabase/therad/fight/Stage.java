package com.janloong.javabase.therad.fight;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-02-25 13:31
 */
public class Stage extends Thread {

    @Override
    public void run() {

        ArmyRunable suiJunTask = new ArmyRunable();
        ArmyRunable nongMinJunTask = new ArmyRunable();

        Thread suiJun = new Thread(suiJunTask, "隋军");
        Thread nongMinJun = new Thread(nongMinJunTask, "农民军");

        suiJun.start();
        nongMinJun.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
        System.out.println("正当双方激战正酣，杀出了大侠");
        Thread keyPerson = new KeyPerson();
        keyPerson.setName("大侠");


        suiJunTask.isFight = false;
        nongMinJunTask.isFight = false;
        //suiJun.stop();
        //nongMinJun.stop();

        keyPerson.start();
        try {
            //suiJun.join();
            keyPerson.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Stage().start();
    }
}
