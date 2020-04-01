package com.janloong.springsecurity.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-04-01 00:15
 */
@Component
public class MqConfig {
    public static final String NORMAL_QUEUE = "doo-queue-1";

    public static final String TOPIC_QUEUE1 = "doo.topic.queue1";
    public static final String TOPIC_QUEUE2 = "doo.topic.queue2";
    public static final String TOPIC_QUEUE3 = "doo.topic.queue3";
    public static final String TOPIC_EXCHANGE = "doo.topic.exchange";

    public static final String TOPIC_ROUTING_KEY = "doo.topic.queue1";
    public static final String TOPIC_ROUTING_KEY2 = "doo.topic.*";
    public static final String TOPIC_ROUTING_KEY3 = "doo.#";


    @Bean
    public Queue doo1() {
        return new AnonymousQueue(() -> NORMAL_QUEUE);
    }

    @Bean
    public Queue doo2() {
        return new Queue(TOPIC_QUEUE1);
    }

    @Bean
    public Queue doo3() {
        return new Queue(TOPIC_QUEUE2);
    }

    @Bean
    public Queue doo4() {
        return new Queue(TOPIC_QUEUE3);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingExchangeMessage() {
        return BindingBuilder.bind(doo2()).to(exchange()).with(TOPIC_ROUTING_KEY);
    }

    @Bean
    public Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(doo3()).to(exchange()).with(TOPIC_ROUTING_KEY2);
    }

    @Bean
    public Binding bindingExchangeMessage3() {
        return BindingBuilder.bind(doo4()).to(exchange()).with(TOPIC_ROUTING_KEY3);
    }
}
