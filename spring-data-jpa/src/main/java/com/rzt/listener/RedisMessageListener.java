package com.rzt.listener;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-22 13:57
 */
@Configuration
public class RedisMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();
        System.out.println("新消息: " + new String(channel) + " = " + new String(channel) + " = " + new String(channel));

    }
}
