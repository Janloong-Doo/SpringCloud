/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: SignFilter.java
 : Author: janloongdoo@gmail.com
 : Date: 19-7-11 下午5:20
 : LastModify: 19-7-11 下午5:20
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.core;


import com.alibaba.fastjson.JSONObject;
import com.janloong.basestudy.utils.SignUtil;
import com.janloong.common.enums.ResultEnum;
import com.janloong.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-11 17:20
 */
@Slf4j
//@Component
public class SignFilter implements Filter {

    @Autowired
    CacheManager cacheManager;

    static final String FAVICON = "/favicon.ico";

    @Override
    public void init(FilterConfig filterConfig) {

        log.info("初始化 SignAuthFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest request = (HttpServletRequest) req;
        //HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        HttpServletRequest requestWrapper = new HttpServletRequestWrapper(request);
        //获取图标不需要验证签名
        if (FAVICON.equals(requestWrapper.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            //获取全部参数(包括URL和body上的)
            SortedMap<String, String> allParams = SignUtil.getAllParams(requestWrapper);
            boolean repeatAble = SignUtil.verifyNonce(allParams, cacheManager);
            //对参数进行签名验证
            boolean isSigned = SignUtil.verifySign(allParams);
            if (!isSigned || repeatAble) {
                log.info("参数校验出错:\n sign:" + isSigned + "- repeat:" + repeatAble);
                //校验失败返回前端
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.setStatus(400);
                PrintWriter out = response.getWriter();
                ResponseResult error = ResponseResult.error(ResultEnum.REQUEST_ERROR.getCode(), ResultEnum.REQUEST_ERROR.getMsg());
                out.append(JSONObject.toJSONString(error));
            } else {
                log.info("签名通过");
                chain.doFilter(requestWrapper, response);
            }
        }
    }


    @Override
    public void destroy() {

        log.info("销毁 SignAuthFilter");
    }
}
