/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: MsgSendFactory.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/4/2 下午2:10
 * LastModify: 2020/4/2 下午2:10
 */

package com.janloong.springsecurity.rabbitmq.handler;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-04-02 14:10
 **/
public interface MsgSendHandler {

    String getSendType();

    Object sendMsg(RabbitTemplate rabbitTemplate);
}
