/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: BusinessException.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午5:41
 : LastModify: 18-10-16 上午9:28
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.common.exception;


import com.janloong.baseframework.common.enums.ResultEnum;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-05-14 9:26
 */
public class BusinessException extends RuntimeException {
    private Integer code;
    private String msg;
    private boolean isSuccess = false;

    public BusinessException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.name();
    }

    public BusinessException(ResultEnum resultEnum, boolean isSuccess) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.name();
        this.isSuccess = isSuccess;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
