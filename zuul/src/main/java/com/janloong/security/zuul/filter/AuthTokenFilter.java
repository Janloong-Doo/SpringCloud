package com.janloong.security.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * 李成阳
 * 2018/2/5
 * 路由过滤器
 */
public class AuthTokenFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);
    //@Autowired
    //private RedisTemplate<String, Object> redisTemplate;
    //@Autowired
    //private AuthCenterFeign authCenterFeign;

    @Override

    //pre 证明是前置过滤器
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    //执行顺序   数字越大 优先级越低
    public int filterOrder() {
        return 0;
    }

    @Override
    //为true时证明开启了过滤器
    public boolean shouldFilter() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        if (requestURI.contains("/authcenter")
                || requestURI.contains("/img")
                || requestURI.contains("/prod")
                || requestURI.contains("/upload")
                || requestURI.contains("/hangzhoubaogongdian")
                || requestURI.contains("/hzsdxz")
        ) {
            return false;
        }
        return true;
        //return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token1 = request.getHeader("Authorization");
        //String token1 = request.getHeader("janloong-token");
        //校验token 是否有效
        if (StringUtils.isEmpty(token1)) {
            System.out.println("token为空");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("token null");
            //ctx.setResponseBody(WebApiResponse.erro("token null").toString());
            return null;
        } else {
            //WebApiResponse validate = authCenterFeign.validateToken(token1);
            //boolean success = validate.isSuccess();
            //LinkedHashMap data = null;
            //boolean aBoolean = false;
            //if (success) {
            //    data = (LinkedHashMap) validate.getData();
            //    String tokenStatus = (String) data.get("tokenStatus");
            //    aBoolean = Boolean.parseBoolean(tokenStatus);
            //}
            //System.out.println(aBoolean);
            //if (success && aBoolean) {
            //    System.out.println("token校验成功");
            //    return null;
            //} else {
            //    System.out.println("token校验错误");
            //    ctx.setSendZuulResponse(false);
            //    ctx.setResponseStatusCode(401);
            //    //ctx.setResponseBody(WebApiResponse.erro("token error").toString());
            //    ctx.setResponseBody("token无效");
                return null;
            //}
        }
    }
}

