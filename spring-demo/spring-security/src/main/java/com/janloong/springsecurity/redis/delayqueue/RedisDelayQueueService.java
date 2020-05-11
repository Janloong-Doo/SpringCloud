package com.janloong.springsecurity.redis.delayqueue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * 延迟队列的redis实现
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020-05-11 23:32
 */
@Service
public class RedisDelayQueueService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void sendMsgToQueue() {
        DelayQueue<Delayed> delayeds = new DelayQueue<>();

    }

}
