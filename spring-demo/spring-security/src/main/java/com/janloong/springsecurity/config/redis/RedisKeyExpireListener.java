/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: RedisKeyExpireListener.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/9 下午1:55
 * LastModify: 2020/1/9 下午1:55
 */

package com.janloong.springsecurity.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.config.redis
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-09 13:55
 **/
@Slf4j
@Component
public class RedisKeyExpireListener extends KeyExpirationEventMessageListener {

    //@Autowired
    //private RedisTemplate redisTemplate;

    public RedisKeyExpireListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        log.info("过期的key为：{}", expiredKey);
        //super.onMessage(message, pattern);
    }

}
