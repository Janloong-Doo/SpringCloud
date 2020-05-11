/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: Base64Turn.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/5/11 下午5:14
 * LastModify: 2020/5/11 下午5:14
 */

package com.janloong.javabase.Base;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络传输中中文乱码 （Oracle http请求过程）
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020-05-11 17:14
 **/
public class Base64Turn {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String send = send();
        //处理后中文不乱吗
        String receive = receive(send());
    }

    private static String send() throws UnsupportedEncodingException {
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        String msg = "我是中文哟, By Janloong Doo";
        byte[] encode = Base64.getEncoder().encode(msg.getBytes("utf-8"));
        //直接返回字节码后被序列换为字符串
        return "JXU1RThGJXU1MjE3JXU1MzE2JXU1NDBFJXU3Njg0bXNn";
    }

    private static String receive(String receiveMsg) throws UnsupportedEncodingException {
        byte[] decode = Base64.getDecoder().decode(receiveMsg.getBytes());
        return new String(Base64.getDecoder().decode(decode), "utf-8");

    }
}
