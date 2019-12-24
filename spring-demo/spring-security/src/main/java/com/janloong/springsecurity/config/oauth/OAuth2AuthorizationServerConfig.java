package com.janloong.springsecurity.config.oauth;

import com.janloong.springsecurity.config.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * AuthorizationServerConfigurer 需要配置三个配置-重写几个方法：
 * <p>
 * ClientDetailsServiceConfigurer： 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
 * <p>
 * AuthorizationServerSecurityConfigurer： 用来配置令牌端点(Token Endpoint)的安全约束.
 * <p>
 * AuthorizationServerEndpointsConfigurer： 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
 * <p>
 * Created by xw on 2017/3/16. 2017-03-16 22:28
 */
@EnableAuthorizationServer
@Configuration
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    // 注入权限验证控制器 来支持 password grant type
    @Autowired
    private AuthenticationManager authenticationManager;
    // 注入认证管理器
    //	private Base64UrlCodec base64UrlCodec = new Base64UrlCodec();
    //注入userDetailsService，开启refresh_token需要用到
    @Autowired
    private UserAuthService userAuthService;

    /**
     * 配置OAuth2的客户端相关信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //String secret = bCryptPasswordEncoder.encode("secret");
        // 使用特定的方式存储client detail
        //clients.withClientDetails(clientDetails())
        clients.jdbc(dataSource)
        // 使用内存存储clientId,secret
        //clients.inMemory()
        ////client1   密码模式
        //.withClient("doo-pass")
        //.resourceIds("doo-pass")
        ////.secret(secret)
        //.secret("secret")
        //.authorizedGrantTypes("password", "refresh_token")
        ////.authorizedGrantTypes("password", "authorization_code", "refresh_token")
        //.scopes("bar", "read", "write")
        //.accessTokenValiditySeconds(3600) // 1 hour
        //.refreshTokenValiditySeconds(2592000) // 30 days
        ////.redirectUris("127.0.0.1")
        //.additionalInformation("janloong001")
        ////client2  客户端模式
        //.and()
        //.withClient("doo-credentials")
        //.redirectUris("127.0.0.1")
        //.additionalInformation("janloong001")
        //.resourceIds("doo-credentials")
        //.secret("secret")
        //.authorizedGrantTypes("client_credentials", "refresh_token")
        //.scopes("bar", "read", "write")
        ////.authorities("")
        //.accessTokenValiditySeconds(3600) // 1 hour
        //.refreshTokenValiditySeconds(2592000) // 30 days
        ////
        //.and()
        //.withClient("doo-code")
        //.redirectUris("127.0.0.1")
        //.additionalInformation("loong")
        //.resourceIds("doo-code")
        //.secret("secret")
        //.authorizedGrantTypes("authorization_code", "refresh_token")
        //.scopes("bar", "read", "write")
        ////.authorities("")
        //.accessTokenValiditySeconds(3600) // 1 hour
        //.refreshTokenValiditySeconds(2592000) // 30 days
        ;
    }

    /**
     * 用来配置令牌端点tokenendopit的安全约束。配置AuthorizationServerEndpointsConfigurer众多相关类，包括配置身份认证器， 配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //指定认证管理器
        endpoints
                .authenticationManager(authenticationManager)
                //要使用refresh_token的话，需要额外配置userDetailsService
                .userDetailsService(userAuthService)
                //自定义登录或者鉴权失败时的返回信息
                //.exceptionTranslator()
                //指定token存储位置
                .tokenStore(tokenStore());

        // 自定义token生成方式
        customGenerateToken(endpoints);

        //配置TokenServices参数
        DefaultTokenServices tokenServices = (DefaultTokenServices) endpoints.getDefaultAuthorizationServerTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1)); // 1天
        endpoints.tokenServices(tokenServices);

        super.configure(endpoints);
    }


    /**
     * 来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //配置AuthorizationServer安全认证的相关信息，
        //创建ClientCredentialsTokenEndpointFilter核心过滤器
        security
                .tokenKeyAccess("permitAll()")
                //.tokenKeyAccess("isAuthenticated()")
                //.checkTokenAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();

        corsConfig(security);

    }

    private void customGenerateToken(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(customerEnhancer(), accessTokenConverter()));
        endpoints.tokenEnhancer(tokenEnhancerChain);
    }
    /**
     * 配置oauth2服务跨域
     */
    private void corsConfig(AuthorizationServerSecurityConfigurer security) {
        CorsConfigurationSource source = new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.addAllowedHeader("*");
                corsConfiguration.addAllowedOrigin(request.getHeader(HttpHeaders.ORIGIN));
                corsConfiguration.addAllowedMethod("*");
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setMaxAge(3600L);
                return corsConfiguration;
            }
        };

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients()
                .addTokenEndpointAuthenticationFilter(new CorsFilter(source));
    }


    /**
     * 定义clientDetails存储的方式-》Jdbc的方式，注入DataSource
     *
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }


    /**
     * 指定token存储的位置
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }


    /**
     * 注入自定义token生成方式
     *
     * @return
     */
    @Bean
    public TokenEnhancer customerEnhancer() {
        return new CustomTokenEnhancer();
    }


    @Bean
    public TokenEnhancer accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //KeyStoreKeyFactory keyStoreKeyFactory =
        //        new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
        //converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
        //非对称加密
        //new RSAKeyValueResolver().engineGetProperty();
        //KeyPair keyPair = new KeyPair();
        //converter.setKeyPair();
        //对称加密
        converter.setSigningKey("janloongdoo");
        converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
        return converter;
    }

}
