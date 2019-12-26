/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AuthSuccessHandler.java
 : Author: janloongdoo@gmail.com
 : Date: 19-7-25 下午4:14
 : LastModify: 19-7-25 下午4:14
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.config.handler;


import com.alibaba.fastjson.JSONObject;
import com.janloong.common.utils.ResponseResult;
import com.janloong.springsecurity.entity.UserDetailImpl;
import com.nimbusds.oauth2.sdk.util.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-25 15:59
 */
@Slf4j
@Component
//public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        log.info("用戶: " + userDetails.getUsername() + " 登录");

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info(savedRequest.getRedirectUrl());
            if (!StringUtils.isEmpty(targetUrl)) {
                redirectStrategy.sendRedirect(request, response, targetUrl);
            }
        } else {
            OAuth2AccessToken oauth2Token = getOauth2Token(request, response, authentication);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            writer.write(JSONObject.toJSONString(ResponseResult.success(oauth2Token)));
            writer.flush();
            writer.close();
        }
    }


    private OAuth2AccessToken getOauth2Token(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Map<String, String> params = new HashMap<>();
        String clientId = request.getParameter("client_id");
        String clientSecret = request.getParameter("client_secret");
        String scope = request.getParameter("scope");
        params.put("username", request.getParameter("username"));
        params.put("password", request.getParameter("password"));
        params.put("grant_type", request.getParameter("grant_type"));
        params.put("imageCode", request.getParameter("imageCode"));
        params.put("scope", scope);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId 不存在" + clientId);
            //判断  方言  是否一致
        //} else if (!PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(clientSecret, clientDetails.getClientSecret())) {
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret 不匹配" + clientId);
        }
        //密码授权 模式, 组建 authentication
        //Map result = new HashMap();
        //result.put("user_name", authentication.getName());
        //result.put("name", ((UserDetailImpl) authentication.getPrincipal()).getUsername());
        //result.put("id", ((UserDetailImpl) authentication.getPrincipal()).getId());
        //result.put("createTime", ((UserDetailImpl) authentication.getPrincipal()).getCreateTime());
        //if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
        //    result.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        //}

        TokenRequest tokenRequest = new TokenRequest(params, clientId, Collections.singletonList(scope), "password");
        //TokenRequest tokenRequest = new TokenRequest(result, clientId, clientDetails.getScope(), "password");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        return authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
    }
}
