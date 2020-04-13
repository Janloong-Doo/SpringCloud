/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: AioServer.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/4/13 下午3:19
 * LastModify: 2020/4/13 下午3:19
 */

package com.janloong.javabase.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-04-13 15:19
 **/
public class AioServer {

    private ExecutorService service;

    private AsynchronousServerSocketChannel serverChannel;

    public ExecutorService getService() {
        return service;
    }

    public AsynchronousServerSocketChannel getServerChannel() {
        return serverChannel;
    }

    public AioServer(int port) {
        init(port);
    }



    private void init(int port) {
        System.out.println("server starting at port "+port+"..");
        // 初始化定长线程池
        service = Executors.newFixedThreadPool(4);
        try {
            // 初始化 AsyncronousServersocketChannel
            serverChannel = AsynchronousServerSocketChannel.open();
            // 监听端口
            serverChannel.bind(new InetSocketAddress(port));
            // 监听客户端连接,但在AIO，每次accept只能接收一个client，所以需要
            // 在处理逻辑种再次调用accept用于开启下一次的监听
            // 类似于链式调用
            serverChannel.accept(this, new AioHandler());

            try {
                // 阻塞程序，防止被GC回收
                TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AioServer(8000);
    }

}
