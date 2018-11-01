/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: Receiver.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-31 上午9:38
 : LastModify: 18-10-31 上午9:38
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.security.user.mq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-10-31 9:38
 */
@Component
@Slf4j
public class Receiver {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        log.info("Msg1 result: " + message);
        countDownLatch.countDown();
    }

    public void receiveMessage2(String message) {
        log.info("Msg2 result: " + message);
        countDownLatch.countDown();
    }

    public CountDownLatch getLatch() {
        return countDownLatch;
    }
}
