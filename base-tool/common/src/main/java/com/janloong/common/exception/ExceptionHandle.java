/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ExceptionHandle.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 上午11:37
 : LastModify: 18-11-1 下午5:17
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.exception;

import com.janloong.common.entity.ErrorInfo;
import com.janloong.common.enums.ResultEnum;
import com.janloong.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 异常处理类
 * <p>
 * controller层异常外抛处理类
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2018/5/15 10:39
 **/
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    @ResponseBody
    public ResponseResult handle(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException bussinesException = (BusinessException) e;
            ErrorInfo errorInfo = bussinesException.getErrorInfo();
            if (errorInfo.isSuccess()) {
                log.info("[业务正常] [{} - {}]", errorInfo.getCode(), errorInfo.getMsg());
                return ResponseResult.success(errorInfo.getCode(), errorInfo.getMsg(), null);
            } else {
                log.error("[业务异常] [{} - {}]", errorInfo.getCode(), errorInfo.getMsg());
                return ResponseResult.error(errorInfo.getCode(), errorInfo.getMsg());
            }
        } else {
            log.error("[系统异常]: {}", e);
            return ResponseResult.error(ResultEnum.ERROR.getCode(), e.getMessage());
        }
    }
}
