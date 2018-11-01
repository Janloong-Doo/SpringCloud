/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: Receiver.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-31 下午3:53
 : LastModify: 18-10-31 下午3:53
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.provide.mq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-10-31 15:53
 */
@Slf4j
@Configuration
public class Receiver {

    @RabbitListener(queues = "doo-queue")
    public void receiver1(String msg) {
        log.info("provide-queue1: " + msg);
    }

    @RabbitListener(queues = "doo-queue2")
    public void receiver2(String msg) {
        log.info("provide-queue2: " + msg);
    }


}
