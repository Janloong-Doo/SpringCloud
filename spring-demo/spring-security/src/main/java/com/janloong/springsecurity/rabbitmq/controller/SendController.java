package com.janloong.springsecurity.rabbitmq.controller;


import com.janloong.common.utils.ResponseResult;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private AmqpTemplate amqpTemplate;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/3/30 0030 22:48
     **/
    @RequestMapping("/msg")
    public ResponseResult msg(@RequestParam String name) {
        String s = "hello,Janloong! This is default msg!";
        Message message = new Message(s.getBytes(), MessagePropertiesBuilder.newInstance().build());
        amqpTemplate.send(message);
        amqpTemplate.send("doo-exchange","doo-key",message);
        return ResponseResult.success("发送成功");
    }
}
