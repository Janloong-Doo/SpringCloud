/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HttpsUtils.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 上午11:37
 : LastModify: 18-11-1 下午5:20
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.janloong.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * des: https 请求工具类
 *
 * @author Janloong
 * @create 2017-12-19 11:26
 **/
@Slf4j
public class HttpsUtils {

    public HttpsUtils() {
    }

    public static String get(String url) {
        return url.startsWith("https") ? httpsGet(url, true) : httpsGet(url, false);
    }

    public static String post(String url) {
        return post(url, "");
    }

    public static String post(String url, String data) {
        return url.startsWith("https") ? httpsPost(url, data, true) : httpsPost(url, data, false);
    }

    public static String post(String url, String data, Header[] headers) {
        return url.startsWith("https") ? httpsPost(url, data, headers, true) : httpsPost(url, data, headers, false);
    }

    /**
     * des: form 请求专用
     *
     * @author Janloong
     * @create 2017-12-25 18:39
     **/
    public static String postForm(String url, Map<String, Object> data) {
        return url.startsWith("https") ? httpsPostForm(url, data, true) : httpsPostForm(url, data, false);
    }

    public static String post(String url, Map<String, String> params) throws Exception {
        return url.startsWith("https") ? httpsPost(url, params, true) : httpsPost(url, params, false);
    }

    public static String postFile(String url, String name, File file) {
        return url.startsWith("https") ? httpsPostFile(url, name, file, true) : httpsPostFile(url, name, file, false);
    }

    public static byte[] getFile(String url) {
        return url.startsWith("https") ? httpsGetFile(url, true) : httpsGetFile(url, false);
    }

    private static String httpsPostForm(String url, Map<String, Object> data, boolean isHttps) {
        try {
            HttpClient httpClient = initHttpclient(isHttps);
            HttpPost post = new HttpPost(url);

            if (data != null && data.size() > 0) {
                List<NameValuePair> list = new ArrayList<>();
                data.forEach((j, k) -> {
                    list.add(new BasicNameValuePair(j, k.toString()));
                });
                HttpEntity reqEntity = new UrlEncodedFormEntity(list, "UTF-8");
                post.setEntity(reqEntity);
            }
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String httpsPost(String url, String data, boolean isHttps) {
        try {
            HttpClient httpClient = initHttpclient(isHttps);
            HttpPost post = new HttpPost(url);
            if (data != null && !data.equals("")) {
                HttpEntity reqEntity = new StringEntity(data, "UTF-8");
                post.setEntity(reqEntity);
            }
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (Exception var7) {
            log.error("https post error," + var7.getMessage());
            var7.printStackTrace();
            return null;
        }
    }

    private static String httpsPost(String url, String data, Header[] header, boolean isHttps) {
        try {
            HttpClient httpClient = initHttpclient(isHttps);
            HttpPost post = new HttpPost(url);
            post.setHeaders(header);
            if (data != null && !data.equals("")) {
                HttpEntity reqEntity = new StringEntity(data, "UTF-8");
                post.setEntity(reqEntity);
            }
            post.addHeader("", "");
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (Exception var7) {
            log.error("https post error," + var7.getMessage());
            var7.printStackTrace();
            return null;
        }
    }

    private static String httpsGet(String url, boolean isHttps) {
        try {
            HttpClient httpClient = initHttpclient(isHttps);
            HttpGet get = new HttpGet(url);
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (Exception var6) {
            log.error("https get error," + var6.getMessage());
            var6.printStackTrace();
            return null;
        }
    }

    /**
     * des: map参数post 请求
     *
     * @author Janloong
     * @create 2017-12-18 18:24
     **/
    private static String httpsPost(String url, Map<String, String> params, boolean isHttps) throws Exception {
        HttpClient httpClient = initHttpclient(isHttps);
        RequestBuilder post = RequestBuilder.post(url);
        Charset charset = CharsetUtils.get("UTF-8");
        post.setCharset(charset);
        params.forEach(post::addParameter);
        HttpResponse response = httpClient.execute(post.build());
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        return result;
    }

    /**
     * des: 上传文件
     *
     * @author Janloong
     * @create 2017-12-19 09:29
     **/
    private static String httpsPostFile(String url, String name, File file, boolean isHttps) {
        try {
            HttpClient httpClient = initHttpclient(isHttps);
            RequestBuilder post = RequestBuilder.post(url);
            if (file != null) {
                HttpEntity reqEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.STRICT)
                        .addBinaryBody(name, file).build();
                post.setEntity(reqEntity);
            }
            HttpResponse response = httpClient.execute(post.build());
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception var7) {
            log.error("post请求异常，" + var7.getMessage() + "\n post url:" + url);
            var7.printStackTrace();
            return null;
        }
    }

    /**
     * des: 下载字节文件
     *
     * @author Janloong
     * @create 2017-12-19 09:54
     **/
    private static byte[] httpsGetFile(String url, boolean isHttps) {
        try {
            HttpClient httpClient = initHttpclient(isHttps);
            HttpGet get = new HttpGet(url);
            HttpResponse response = httpClient.execute(get);
            HttpEntity resEntity = response.getEntity();
            return EntityUtils.toByteArray(resEntity);
        } catch (Exception var5) {
            log.error("postFile请求异常，" + var5.getMessage() + "\n post url:" + url);
            var5.printStackTrace();
            return null;
        }
    }

    /**
     * des: 初始化httpclient
     *
     * @author Janloong
     * @create 2017-12-25 18:43
     **/
    private static HttpClient initHttpclient(boolean isHttps) throws KeyManagementException, NoSuchAlgorithmException {
        return isHttps ? HttpsClient.initHttpsClient() : HttpClientBuilder.create().build();
    }
}
