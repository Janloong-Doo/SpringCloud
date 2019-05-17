package com.janloong.baseframework.common.utils;


import com.alibaba.fastjson.JSONObject;
import com.janloong.baseframework.common.enums.RequestEnum;
import com.janloong.baseframework.common.enums.ResultEnum;
import com.janloong.baseframework.common.exception.BusinessException;

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
