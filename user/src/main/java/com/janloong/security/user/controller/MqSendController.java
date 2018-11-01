/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: MqSendController.java
 : Author: janloongdoo@gmail.com
 : Date: 18-11-1 下午5:57
 : LastModify: 18-11-1 下午5:57
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.security.user.controller;


import com.janloong.security.user.mq.MsgSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-11-01 17:57
 */
@RestController
@Slf4j
public class MqSendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/11/1 17:58
     **/
    @RequestMapping("/send")
    public String send(String msg) {
        log.info("入参: " + msg);
        rabbitTemplate.convertAndSend(MsgSender.TOPIC_EXCHANGE_NAME, "foo.bar.baz", msg);
        return "success";
    }
}
