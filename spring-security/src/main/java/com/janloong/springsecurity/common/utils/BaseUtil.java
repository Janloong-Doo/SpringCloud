/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: BaseUtil.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午5:41
 : LastModify: 18-11-1 下午5:17
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.common.utils;


import com.alibaba.fastjson.JSONObject;
import com.janloong.springsecurity.common.enums.RequestEnum;
import com.janloong.springsecurity.common.enums.ResultEnum;
import com.janloong.springsecurity.common.exception.BusinessException;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-08-01 10:36
 */
public class BaseUtil {

    public static Object getRequest(String host, String type) {
        RequestEnum warn = null;
        try {
            warn = RequestEnum.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new BusinessException(ResultEnum.参数错误);
        }
        String key = warn.getKey();
        String s = HttpsUtils.get(host + "/" + key);
        JSONObject jsonObject = JSONObject.parseObject(s);
        Boolean success = jsonObject.getBoolean("success");
        return jsonObject;
    }
}
