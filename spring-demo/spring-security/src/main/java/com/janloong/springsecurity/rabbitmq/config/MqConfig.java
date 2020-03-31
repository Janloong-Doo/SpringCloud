package com.janloong.springsecurity.rabbitmq.config;


import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-04-01 00:15
 */
@Component
public class MqConfig {

    @Bean
    public Queue doo1() {
        return new AnonymousQueue(() -> "doo-queue-1");
    }
}
