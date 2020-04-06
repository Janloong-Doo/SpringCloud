package com.janloong.springsecurity.queue;


import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-04-06 17:12
 */
public class QueueDemo {

    public static void main(String[] args) {
        LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue<>();
        for (int i = 0; i < 20; i++) {
            objects.add("queue" + i);
        }

        objects.forEach(System.out::println);
    }
}
