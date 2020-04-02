package com.janloong.springsecurity.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <p>
 * routingKey 不具备模糊匹配字符，不同于bindingKey,会将内容对应为具体字符串去匹配相应的bindingKey
 * </p>
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-04-01 00:15
 */
@Component
public class TopicQueueConfig {

    public static final String TOPIC_QUEUE1 = "doo.topic.queue1";
    public static final String TOPIC_QUEUE2 = "doo.topic.queue2";
    public static final String TOPIC_QUEUE3 = "doo.topic.queue3";
    public static final String TOPIC_EXCHANGE = "doo.topic.exchange";

    public static final String TOPIC_ROUTING_KEY = "doo.topic.queue1";
    public static final String TOPIC_ROUTING_KEY2 = "doo.topic.*";
    public static final String TOPIC_ROUTING_KEY3 = "doo.#";



    @Bean(name = "doo2")
    public Queue doo2() {
        return new Queue(TOPIC_QUEUE1);
    }

    @Bean(name = "doo3")
    public Queue doo3() {
        return new Queue(TOPIC_QUEUE2);
    }

    @Bean(name = "doo4")
    public Queue doo4() {
        return new Queue(TOPIC_QUEUE3);
    }

    @Bean("topic_exchange")
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingExchangeMessage(@Qualifier("doo2") Queue queue, @Qualifier("topic_exchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(TOPIC_ROUTING_KEY);
    }

    @Bean
    public Binding bindingExchangeMessage2(@Qualifier("doo3") Queue queue, @Qualifier("topic_exchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(TOPIC_ROUTING_KEY2);
    }

    @Bean
    public Binding bindingExchangeMessage3(@Qualifier("doo4") Queue queue, @Qualifier("topic_exchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(TOPIC_ROUTING_KEY3);
    }
}
