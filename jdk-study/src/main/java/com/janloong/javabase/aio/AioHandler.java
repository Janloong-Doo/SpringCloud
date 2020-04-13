/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: AioHandler.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/4/13 下午3:20
 * LastModify: 2020/4/13 下午3:20
 */

package com.janloong.javabase.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-04-13 15:20
 **/
public class AioHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
    @Override
    public void completed(AsynchronousSocketChannel result, AioServer attachment) {
        // 处理下一次的client连接。类似链式调用
        attachment.getServerChannel().accept(attachment, this);

        // 执行业务逻辑
        doRead(result);
    }

    /**
     * 读取client发送的消息打印到控制台
     *
     * AIO中OS已经帮助我们完成了read的IO操作，所以我们直接拿到了读取的结果
     *
     *
     * @param clientChannel 服务端于客户端通信的 channel
     */
    private void doRead(AsynchronousSocketChannel clientChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 从client读取数据,在我们调用clientChannel.read()之前OS，已经帮我们完成了IO操作
        // 我们只需要用一个缓冲区来存放读取的内容即可
        clientChannel.read(
                buffer,   // 用于数据中转缓冲区
                buffer,   // 用于存储client发送的数据的缓冲区
                new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        System.out.println("receive client data length：" + attachment.capacity() + " byte");
                        attachment.flip(); // 移动 limit位置
                        // 读取client发送的数据
                        System.out.println("from client : "+new String(attachment.array(), StandardCharsets.UTF_8));

                        // 向client写入数据
                        doWrite(clientChannel);
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                    }
                }
        );
    }

    private void doWrite(AsynchronousSocketChannel clientChannel) {

        // 向client发送数据，clientChannel.write()是一个异步调用，该方法执行后会通知
        // OS执行写的IO操作，会立即返回
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        buffer.put(line.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        clientChannel.write(buffer);
        // clientChannel.write(buffer).get(); // 会进行阻塞，直到OS写操作完成
    }

    /**
     * 异常处理逻辑
     *
     * @param exc
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.printStackTrace();
    }
}
