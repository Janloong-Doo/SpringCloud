package com.janloong.auth2center.config;

import com.janloong.auth2center.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;


/**
 * token生成携带的信息
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication != null) {
            User user = (User) userAuthentication.getPrincipal();
            additionalInfo.put("name", user.getName());
            additionalInfo.put("username", user.getUsername());
            additionalInfo.put("authorities", user.getAuthorities());
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        System.out.println("token信息: " + accessToken);
        return accessToken;
    }

}
