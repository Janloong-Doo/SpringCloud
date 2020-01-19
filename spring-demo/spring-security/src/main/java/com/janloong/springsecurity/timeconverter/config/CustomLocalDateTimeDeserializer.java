/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: CustomLocalDateTimeDeserializer.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/1/14 下午7:46
 * LastModify: 2020/1/14 下午7:46
 */

package com.janloong.springsecurity.timeconverter.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @version V1.0
 * @program: cloud
 * @package: com.janloong.springsecurity.timeconverter.config
 * @author: <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @create: 2020-01-14 19:46
 **/
//@Configuration
public class CustomLocalDateTimeDeserializer  extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String dateStr = p.getText();
        LocalDateTime parse = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(""));

        //ZoneId zoneId = ZoneId.systemDefault();
        //LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return parse;
    }

    @Override
    public Class<?> handledType() {
        return LocalDateTime.class;
    }
}
