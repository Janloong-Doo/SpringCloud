package com.janloong.javabase.therad.base;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 线程计数器 - 阻塞线程直至计数器为0
 * <p>
 * 当生命一个线程计数器时， 调用await方法可以使声明该计数器的主线程处于阻塞状态。
 * 调用countDown方法会使计数器的数值减1，一般用在子线程中，当所有子线程都运行完毕后才运行主线程
 *
 * </p>
 * <p>
 * 试用场景:
 * 1.  任务进行拆分进行并行执行。
 * 2.  批量进行网络请求的时候
 * </p>
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-04-01 00:34
 */
public class Countdownlatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        Service service = new Service(latch);
        Runnable task = () -> service.exec();

        //声明五个子线程
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        System.out.println("main thread await. ");
        latch.await();
        System.out.println("main thread finishes await. ");
    }
}

class Service {
    private CountDownLatch latch;

    public Service(CountDownLatch latch) {
        this.latch = latch;
    }

    public void exec() {
        try {
            System.out.println(Thread.currentThread().getName() + " execute task. ");
            sleep(2);
            System.out.println(Thread.currentThread().getName() + " finished task. ");
        } finally {
            //执行完毕，计数器减1
            latch.countDown();
        }
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
