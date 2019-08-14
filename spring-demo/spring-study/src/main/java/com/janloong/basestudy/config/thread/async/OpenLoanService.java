/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: OpenLoanService.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/8/14 下午5:44
 : LastModify: 2019/8/14 下午5:44
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.config.thread.async;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-08-14 17:44
 */
@Slf4j
public class OpenLoanService {

    @Autowired
    private LoanCheckTask loanCheckTask;

    public boolean openLoanService() {
        long s = System.currentTimeMillis();
        CompletableFuture userInfoFuture = loanCheckTask.getUserInfo();
        CompletableFuture bankCreditInfoFuture = loanCheckTask.getBankCreditInfo();
        CompletableFuture<Boolean> future = userInfoFuture.thenCombine(bankCreditInfoFuture,
                (BiFunction<String, String, Boolean>) (x, y) -> loanCheckTask.getAllowLoan(x, y));
        Boolean join = future.join();
        log.info("开通贷款服务time={}",System.currentTimeMillis()-s);
        return join;
    }

}
