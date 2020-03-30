///*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
// : Copyright (c) 2018  All Rights Reserved.
// : ProjectName: SpringCloud
// : FileName: Receiver.java
// : Author: janloongdoo@gmail.com
// : Date: 18-10-31 上午9:38
// : LastModify: 18-10-31 上午9:38
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
//
//package com.janloong.springsecurity.rabbitmq.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CountDownLatch;
//
///**
// * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
// * @date 2018-10-31 9:38
// */
//@Component
//@Slf4j
//public class Receiver {
//
//    private CountDownLatch countDownLatch = new CountDownLatch(1);
//
//    @RabbitListener(queues = "doo-queue1")
//    public void receiveMessage(String message) {
//        log.info("user queue1: " + message);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        countDownLatch.countDown();
//    }
//
//    @RabbitListener(queues = "doo-queue2")
//    public void receiveMessage2(String message) {
//        log.info("user queue2: " + message);
//        countDownLatch.countDown();
//    }
//
//    public CountDownLatch getLatch() {
//        return countDownLatch;
//    }
//}
