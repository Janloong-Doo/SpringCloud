package com.janloong.springsecurity.config.oauth;

import com.janloong.springsecurity.entity.User;
import com.janloong.springsecurity.entity.UserDetailImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;


/**
 * token扩展类 增加token生成携带的信息
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication != null) {
            UserDetailImpl user = (UserDetailImpl) userAuthentication.getPrincipal();
            additionalInfo.put("username", user.getUsername());
            additionalInfo.put("authorities", user.getAuthorities());
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        System.out.println("token信息: " + accessToken);
        return accessToken;
    }

}
