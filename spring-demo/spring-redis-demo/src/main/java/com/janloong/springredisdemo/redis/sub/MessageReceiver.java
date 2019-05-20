/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: MessageReceiver.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 下午3:35
 : LastModify: 19-5-20 下午3:34
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springredisdemo.redis.sub;


import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-20 15:32
 */
@Component
public class MessageReceiver {
    public void receiveMessage(String message) {
        System.out.println("收到一条消息：" + message);
    }

    public void receiveMessage2(String message) {
        System.out.println("收到一条消息2：" + message);
    }
}
