package com.janloong.baseframework.controller;


import com.alibaba.fastjson.JSONObject;
import com.janloong.zdhconsume.utils.BaseUtil;
import com.janloong.zdhconsume.utils.HttpsUtils;
import com.janloong.zdhconsume.utils.WebApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-07-31 17:48
 */
@RestController
public class WarnController {

    @Value("${provider.host}")
    private String providerHost;


    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/8/1 10:15
     **/
    @RequestMapping("/warnInfo")
    public Object warnInfo(@RequestParam String type) {
        return BaseUtil.getRequest(providerHost, type);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/7/31 17:50
     **/
    @RequestMapping("/test")
    public WebApiResponse ts(String name) {
        String s = HttpsUtils.get(providerHost + "/test1");
        JSONObject jsonObject = JSONObject.parseObject(s);
        return WebApiResponse.success(jsonObject);
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/7/31 17:50
     **/
    @RequestMapping("/test3")
    public WebApiResponse ts2(String name) {
        String s = HttpsUtils.get(providerHost + "/test2");
        JSONObject jsonObject = JSONObject.parseObject(s);
        return WebApiResponse.success(jsonObject);
    }
}
