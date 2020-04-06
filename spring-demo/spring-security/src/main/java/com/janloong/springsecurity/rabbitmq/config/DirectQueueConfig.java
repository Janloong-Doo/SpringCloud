package com.janloong.springsecurity.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 消息只会路由到routingKey和bindingKey相同的queue上
 * </p>
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-04-01 00:15
 */
@Component
public class DirectQueueConfig {

    public static final String TOPIC_QUEUE1 = "doo.direct.queue1";
    public static final String TOPIC_QUEUE2 = "doo.direct.queue2";
    public static final String TOPIC_QUEUE3 = "doo.direct.queue3";
    public static final String TOPIC_EXCHANGE = "doo.direct.exchange";

    public static final String TOPIC_ROUTING_KEY = "doo.direct.queue1";
    public static final String TOPIC_ROUTING_KEY2 = "doo.direct.*";
    public static final String TOPIC_ROUTING_KEY3 = "doo.#";


    @Bean(name = "direct_doo2")
    public Queue doo2() {
        return new Queue(TOPIC_QUEUE1);
    }

    @Bean(name = "direct_doo3")
    public Queue doo3() {
        return new Queue(TOPIC_QUEUE2);
    }

    @Bean(name = "direct_doo4")
    public Queue doo4() {
        return new Queue(TOPIC_QUEUE3);
    }

    @Bean("direct_exchange")
    public DirectExchange exchange() {
        return new DirectExchange(TOPIC_EXCHANGE);
    }

    @Bean("direct_bindingkey1")
    public Binding bindingExchangeMessage(@Qualifier("direct_doo2") Queue queue, @Qualifier("direct_exchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(TOPIC_ROUTING_KEY);
    }

    @Bean("direct_bindingkey2")
    public Binding bindingExchangeMessage2(@Qualifier("direct_doo3") Queue queue, @Qualifier("direct_exchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(TOPIC_ROUTING_KEY2);
    }

    @Bean("direct_bindingkey3")
    public Binding bindingExchangeMessage3(@Qualifier("direct_doo4") Queue queue, @Qualifier("direct_exchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(TOPIC_ROUTING_KEY3);
    }
}
