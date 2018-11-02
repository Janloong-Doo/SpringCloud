/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: MsgSender.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-31 上午10:44
 : LastModify: 18-10-31 上午10:44
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.security.user.mq;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq sender
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-10-31 10:44
 */
@Configuration
public class MsgSender {
    public static final String QUEUE_NAME = "doo-queue1";
    public static final String QUEUE_NAME2 = "doo-queue2";
    public static final String QUEUE_NAME3 = "doo-queue3";
    public static final String TOPIC_EXCHANGE_NAME = "topic-exchange";
    public static final String FANOUT_EXCHANGE_NAME = "fanout-exchange";

    @Bean(name = "queue1")
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean(name = "queue2")
    Queue queue2() {
        return new Queue(QUEUE_NAME2, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    FanoutExchange exchange2() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }

    @Bean
    Binding topicBinding(@Qualifier("queue1") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("A.B.#");
    }

    @Bean
    Binding topicBinding2(@Qualifier("queue2") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("A.#.#");
    }

    @Bean
    Binding fanoutBinding(@Qualifier("queue1") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    Binding fanoutBinding2(@Qualifier("queue2") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


    //@Bean
    //SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
    //                                         MessageListenerAdapter listenerAdapter) {
    //    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    //    container.setConnectionFactory(connectionFactory);
    //    container.setQueueNames(QUEUE_NAME, QUEUE_NAME2);
    //    container.setMessageListener(listenerAdapter);
    //    return container;
    //}
    ////
    //@Bean
    //MessageListenerAdapter listenerAdapter(Receiver receiver) {
    //    return new MessageListenerAdapter(receiver, "receiveMessage2");
    //}


    //@Bean
    //DirectMessageListenerContainer directContainer(ConnectionFactory connectionFactory) {
    //    DirectMessageListenerContainer directMessageListenerContainer = new DirectMessageListenerContainer(connectionFactory);
    //    directMessageListenerContainer.addQueues(queue(), queue2());
    //    return directMessageListenerContainer;
    //}
}
