package com.janloong.springsecurity.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 扇形交换类型无需配置bindingKey 配置也不起作用
 * </p>
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-04-01 00:15
 */
@Component
public class FanoutQueueConfig {

    public static final String TOPIC_QUEUE1 = "doo.fantout.queue1";
    public static final String TOPIC_QUEUE2 = "doo.fanout.queue2";
    public static final String TOPIC_QUEUE3 = "doo.fanout.queue3";
    public static final String TOPIC_EXCHANGE = "doo.fanout.exchange";

    public static final String TOPIC_ROUTING_KEY = "doo.fanout.queue1";
    public static final String TOPIC_ROUTING_KEY2 = "doo.fanout.*";
    public static final String TOPIC_ROUTING_KEY3 = "doo.#";


    @Bean(name = "fanout_doo2")
    public Queue doo2() {
        return new Queue(TOPIC_QUEUE1);
    }

    @Bean(name = "fanout_doo3")
    public Queue doo3() {
        return new Queue(TOPIC_QUEUE2);
    }

    @Bean(name = "fanout_doo4")
    public Queue doo4() {
        return new Queue(TOPIC_QUEUE3);
    }

    @Bean("fanout_exchange")
    public FanoutExchange exchange() {
        return new FanoutExchange(TOPIC_EXCHANGE);
    }

    @Bean("fanout_binging1")
    public Binding bindingExchangeMessage(@Qualifier("fanout_doo2") Queue queue, @Qualifier("fanout_exchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean("fanout_binging2")
    public Binding bindingExchangeMessage2(@Qualifier("fanout_doo3") Queue queue, @Qualifier("fanout_exchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean("fanout_binging3")
    public Binding bindingExchangeMessage3(@Qualifier("fanout_doo4") Queue queue, @Qualifier("fanout_exchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
