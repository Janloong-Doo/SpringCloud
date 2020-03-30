package com.janloong.springsecurity.rabbitmq.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-03-30 23:17
 */
@Component
public class MsgListener {

    @RabbitListener()
    public void listen(String content) {
        System.out.println(content);
    }
}
