package com.janloong.springsecurity.jdk.concurrent;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 延迟队列测试
 * <p/>
 * See <a href="https://blog.csdn.net/dkfajsldfsdfsd/article/details/88966814">参考blog</a>
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020-05-12 00:27
 */

public class DelayQueueTest {


    public static void main(String[] args) {
        BlockingQueue<DelayTask> queue = new DelayQueue<DelayTask>();
        //添加10个延迟任务，每个任务执行耗时2秒
        for (int i = 0; i < 10; i++) {
            try {
                queue.put(new DelayTask("work " + i, 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ThreadGroup g = new ThreadGroup("Consumers");
        //初始化线程数量i，开始延迟任务的执行
        for (int i = 0; i < 1; i++) {
            new Thread(g, new DelayTaskComsumer(queue)).start();
        }
        while (DelayTask.taskCount.get() > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        g.interrupt();
        System.out.println("Main thread finished");
    }
}

/**
 * 延迟任务
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @since 2020/5/12 0012 23:26
 **/
class DelayTask implements Delayed {
    private long currentTime = System.currentTimeMillis();
    protected final String taskName; //任务名称
    protected final int timeCost; //任务执行耗时
    protected final long scheduleTime;
    protected static final AtomicInteger taskCount = new AtomicInteger(0);

    public DelayTask(String taskName, int timeCost) {
        this.taskName = taskName;
        this.timeCost = timeCost;
        taskCount.incrementAndGet();
        currentTime += 1000 + (long) (Math.random() * 1000);
        scheduleTime = currentTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long expirationTime = scheduleTime - System.currentTimeMillis();
        return unit.convert(expirationTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.scheduleTime - ((DelayTask) o).scheduleTime);
    }


    public void execTask() {
        long startTime = System.currentTimeMillis();
        System.out.println("Task " + taskName + ": schedule_start_time=" + scheduleTime + ",real start time="
                + startTime + ",delay=" + (startTime - scheduleTime));
        try {
            Thread.sleep(timeCost);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class DelayTaskComsumer extends Thread {
    private final BlockingQueue<DelayTask> queue;

    public DelayTaskComsumer(BlockingQueue<DelayTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        DelayTask task = null;
        try {
            //在当前线程持续获取队列中的任务进行执行
            while (true) {
                //获取延迟队列中的延迟任务，当未到延迟时间时会出于阻塞状态无法获取到任务
                task = queue.take();
                task.execTask();
                //任务执行完成后通过让计数器抛出异常终止延迟任务的执行
                DelayTask.taskCount.decrementAndGet();
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " finished");
        }
    }
}

