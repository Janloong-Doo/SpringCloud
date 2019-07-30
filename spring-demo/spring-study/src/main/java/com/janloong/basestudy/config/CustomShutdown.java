package com.janloong.basestudy.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-27 19:08
 */
@Slf4j
public class CustomShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final int TIMEOUT = 30;

    private volatile Connector connector;


    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        //暂停接受来自外部的新请求
        connector.pause();
        //获取connector对应的线程池
        Executor executor = connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            log.warn("web应用即将关闭");
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
            //初始化一个关闭任务,位于当前待处理任务完成之后,并拒绝新任务的提交。
            LocalDateTime now = LocalDateTime.now();
            log.warn("开始关闭:" + now);
            threadPoolExecutor.shutdown();
            try {
                if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    log.warn("应用等待关闭的最大时长为{}秒,之后将强制关闭!", TIMEOUT);
                    log.warn("开始强制关闭:" + LocalDateTime.now());
                    threadPoolExecutor.shutdownNow();
                    if (threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                        log.error("应用强制关闭失败");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
