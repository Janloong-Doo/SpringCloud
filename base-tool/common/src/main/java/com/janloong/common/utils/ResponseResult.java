/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ResponseResult.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-21 上午10:41
 : LastModify: 19-5-21 上午10:41
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.utils;


import lombok.*;

import java.beans.Transient;
import java.io.Serializable;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-21 10:41
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseResult implements Serializable {

    private static final Integer CODE = 0;
    private static final String MSG = "成功";

    @Getter
    private Integer code = CODE;
    @Getter
    private String msg = MSG;
    @Getter
    private Boolean success = true;

    @Getter
    @Setter
    private Object data;

    //@Transient
    public boolean isSuccess() {
        return code == 0;
    }

    public static ResponseResult success() {
        return new ResponseResult();
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(CODE, MSG, true, data);
    }

    public static ResponseResult success(String msg, Object data) {
        return new ResponseResult(CODE, msg, true, data);
    }

    public static ResponseResult success(Integer code, String msg, Object data) {
        return new ResponseResult(code, msg, true, data);
    }

    public static ResponseResult success(Integer code, String msg, Boolean success, Object data) {
        return new ResponseResult(code, msg, success, data);
    }

    public static ResponseResult error(Integer code, String msg) {
        return new ResponseResult(code, msg, false, null);
    }

    static ResponseResult error(Integer code, String msg, Object data) {
        return new ResponseResult(code, msg, false, data);
    }


}
