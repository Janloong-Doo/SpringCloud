/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: BusinessException.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 上午11:37
 : LastModify: 18-10-16 上午9:28
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.exception;


import com.janloong.common.entity.ErrorInfo;
import com.janloong.common.enums.ResultEnum;

/**
 * 自定义异常类（可继承扩展）
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2018-05-14 9:26
 */
public class BusinessException extends RuntimeException {

    private ErrorInfo errorInfo;


    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorInfo.setCode(resultEnum.getCode());
        this.errorInfo.setMsg(resultEnum.name());
    }

    public BusinessException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public BusinessException(ResultEnum resultEnum, boolean isSuccess) {
        this.errorInfo.setCode(resultEnum.getCode());
        this.errorInfo.setMsg(resultEnum.name());
        this.errorInfo.setSuccess(isSuccess);
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}
