/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: LoanCheckTask.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/8/14 下午5:40
 : LastModify: 2019/8/14 下午5:40
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.config.thread.async;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-08-14 17:40
 */
@Component
public class LoanCheckTask {

    /**
     * 贷款前的用户信息检查
     * @return
     */
    @Async
    public CompletableFuture getUserInfo(){
        try {
            sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture( "身份合法");
    }

    /**
     * 贷款前用户的信用信息检查
     * @return
     */
    //@Async
    @Async("myTaskAsyncPool")//使用自定义的线程池执行器
    public CompletableFuture  getBankCreditInfo(){
        try {
            sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture( "银行信用良好");
    }

    /**
     * 开启该用户的贷款权限
     * @return
     */
    public Boolean  getAllowLoan(String userInfo,String creditInfo){
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }
}
