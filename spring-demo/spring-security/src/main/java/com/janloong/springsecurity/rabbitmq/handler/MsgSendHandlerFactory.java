/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: MsgSendHandlerFactory.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/4/2 下午2:16
 * LastModify: 2020/4/2 下午2:16
 */

package com.janloong.springsecurity.rabbitmq.handler;

import com.janloong.common.enums.ResultEnum;
import com.janloong.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-04-02 14:16
 **/
@Component
public class MsgSendHandlerFactory {

    @Autowired
    private List<MsgSendHandler> msgSendHandlers;

    public MsgSendHandler getMsgSendHandler(String sendType) {
        Optional<MsgSendHandler> first = msgSendHandlers.stream().filter(handler -> Objects.equals(handler.getSendType(), sendType)).findFirst();
        if (first.isPresent()) {
            return first.get();
        }
        throw new BusinessException(ResultEnum.ERROR, true);
    }

}
