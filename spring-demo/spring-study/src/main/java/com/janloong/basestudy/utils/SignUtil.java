package com.janloong.basestudy.utils;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-11 16:59
 */
@Slf4j
public class SignUtil {

    /**
     * @param params 所有的请求参数都会在这里进行排序加密
     * @return 验证签名结果
     */
    public static boolean verifySign(SortedMap<String, String> params) {

        String urlSign = params.get("sign");
        log.info("Url Sign : {}", urlSign);
        if (params == null || StringUtils.isEmpty(urlSign)) {
            return false;
        }
        //把参数加密
        String paramsSign = getParamsSign(params);
        log.info("Param Sign : {}", paramsSign);
        return !StringUtils.isEmpty(paramsSign) && urlSign.equals(paramsSign);
    }

    /**
     * @param params 所有的请求参数都会在这里进行排序加密
     * @return 得到签名
     */
    public static String getParamsSign(SortedMap<String, String> params) {
        //要先去掉 Url 里的 Sign
        params.remove("sign");
        String paramsJsonStr = JSONObject.toJSONString(params);
        //return DigestUtils.md5DigestAsHex(paramsJsonStr.getBytes()).toUpperCase();
        //String s = DigestUtils.md5DigestAsHex(paramsJsonStr.getBytes()).toUpperCase();
        //String m = s + "janloong";
        //return DigestUtils.md5DigestAsHex(m.getBytes()).toUpperCase();
        log.info("signParam:\n" + paramsJsonStr);
        String encodeStr = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(paramsJsonStr.getBytes());
            encodeStr = byte2Hex(instance.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

//    ========

    /**
     * 将URL的参数和body参数合并
     *
     * @param request
     * @author show
     * @date 14:24 2019/5/29
     */
    public static SortedMap<String, String> getAllParams(HttpServletRequest request) throws IOException {

        SortedMap<String, String> result = new TreeMap<>();
        //获取URL上的参数
        Map<String, String> urlParams = getUrlParams(request);
        for (Map.Entry entry : urlParams.entrySet()) {
            result.put((String) entry.getKey(), (String) entry.getValue());
        }
        Map<String, String> allRequestParam = new HashMap<>(16);
        // get请求不需要拿body参数
        if (!HttpMethod.GET.name().equals(request.getMethod())) {
            allRequestParam = getAllRequestParam(request);
        }
        //将URL的参数和body参数进行合并
        if (allRequestParam != null) {
            for (Map.Entry entry : allRequestParam.entrySet()) {
                result.put((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return result;
    }

    /**
     * 获取 Body 参数
     *
     * @param request
     * @author show
     * @date 15:04 2019/5/30
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String str = "";
        StringBuilder wholeStr = new StringBuilder();
        //一行一行的读取body体里面的内容；
        while ((str = reader.readLine()) != null) {
            wholeStr.append(str);
        }
        //转化成json对象
        return JSONObject.parseObject(wholeStr.toString(), Map.class);
    }

    /**
     * 将URL请求参数转换成Map
     *
     * @param request
     * @author show
     */
    public static Map<String, String> getUrlParams(HttpServletRequest request) {

        String param = "";
        try {
            param = URLDecoder.decode(request.getQueryString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> result = new HashMap<>(16);
        String[] params = param.split("&");
        for (String s : params) {
            int index = s.indexOf("=");
            result.put(s.substring(0, index), s.substring(index + 1));
        }
        return result;
    }

    public static boolean verifyNonce(SortedMap<String, String> allParams, CacheManager cacheManager) {
        boolean a = false;
        String nonce = allParams.get("nonce");
        String timestamp = allParams.get("timestamp");
        Cache<String, String> defaultCache = cacheManager.getCache("defaultCache", String.class, String.class);
        boolean b = defaultCache.containsKey(nonce);
        System.out.println("timestamp:" + timestamp);
        Instant parse = Instant.ofEpochMilli(Long.parseLong(timestamp)).plusSeconds(60L);
        Instant now = Instant.now();

        log.info("随机数信息：\n"
                + "nonce:" + nonce
                + "\ntimestamp:" + timestamp
                + "\nb:" + b
                + "\ns:" + parse.isBefore(now)
                + "\nold:" + parse.toString()
                + "\nnow:" + now.toString()
        );
        if (parse.isBefore(now) || b) {
            return true;
        }
        defaultCache.put(nonce, "doo");
        return a;
    }
}
