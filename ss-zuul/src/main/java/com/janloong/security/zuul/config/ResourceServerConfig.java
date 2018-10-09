package com.janloong.security.zuul.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-07-20 17:02
 */
@Configuration
@EnableResourceServer
//@EnableWebSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    //public class ResourceServerConfig {
    //@Autowired
    //private OAuth2WebSecurityExpressionHandler expressionHandler;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //http.

        http
                //.antMatcher("/authcenter/**")
                .antMatcher("/usercenter/**")
                .authorizeRequests()
                //.antMatchers("/authcenter/**")
                .anyRequest().authenticated()
        //.and()
        //.antMatcher("/devicecenter/**")
        //.authorizeRequests()
        //.anyRequest()
        //.authenticated()
        //.and()
        //.authorizeRequests()
        ////.permitAll()
        //.anyRequest()
        //.authenticated()
        ;

        http.headers().frameOptions().disable();
        //ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
        //        .authorizeRequests();
        //filterIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        //registry.anyRequest()
        //        //.access("hasRole('ROLE_SUPER')");
        //        .access("permitAll()");
        //.access("@permissionService.hasPzermission(request,authentication)");
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //super.configure(resources);
        //resources.expressionHandler(expressionHandler);
        resources.resourceId("doo-zuul-resource");
        resources.accessDeniedHandler(customAccessDeniedHandler);
        //OAuth2AuthenticationManager oAuth2AuthenticationManager = (OAuth2AuthenticationManager) authenticationManager;
        //oAuth2AuthenticationManager.setTokenServices(getTokenService());
        //resources.authenticationManager(oAuth2AuthenticationManager);
    }

    //@Bean
    //public ResourceServerTokenServices getTokenService() {
    //    RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
    //    return remoteTokenServices;
    //}
}

