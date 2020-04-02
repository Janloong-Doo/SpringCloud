package com.janloong.springsecurity.rabbitmq.controller;


import com.janloong.common.utils.ResponseResult;
import com.janloong.springsecurity.rabbitmq.handler.MsgSendHandlerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-03-30 22:48
 */
@RestController
@RequestMapping("send")
public class SendController {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MsgSendHandlerFactory factory;

    private volatile CountDownLatch latch = new CountDownLatch(2);

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/3/30 0030 22:48
     **/
    @RequestMapping("/msg")
    public ResponseResult msg() throws InterruptedException {
        String s = "hello,Janloong! This is default msg!";
        Message message = new Message(s.getBytes(), MessagePropertiesBuilder.newInstance().build());
        rabbitTemplate.send("doo-queue-1", message);
        latch.await(10, TimeUnit.SECONDS);
        return ResponseResult.success("发送成功");
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/4/2 14:09
     **/
    @RequestMapping("/handlerSend")
    public ResponseResult handlerSend(@RequestParam(defaultValue = "topic") String type) {
        Object topic = factory.getMsgSendHandler(type).sendMsg(rabbitTemplate);
        return ResponseResult.success(topic);
    }
}
