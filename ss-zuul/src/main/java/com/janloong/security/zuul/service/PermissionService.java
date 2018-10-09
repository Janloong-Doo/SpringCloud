/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: PermissionService.java
 : Author: janloongdoo@gmail.com
 : Date: 18-9-28 上午11:37
 : LastModify: 18-9-28 上午11:37
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.security.zuul.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-09-28 11:37
 */
@Service("permissionService")
public class PermissionService {
    private final static Logger log = LoggerFactory.getLogger(PermissionService.class);


    //@Autowired
    //private MenuService menuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //ele-admin options 跨域配置，现在处理是通过前端配置代理，不使用这种方式，存在风险
//        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
//            return true;
//        }
        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        boolean hasPermission = false;

        if (principal != null) {
            if (grantedAuthorityList.isEmpty()) {
                //if (CollectionUtil.isEmpty(grantedAuthorityList)) {
                log.warn("角色列表为空：{}", authentication.getPrincipal());
                return hasPermission;
            }

            //Set<MenuVO> urls = new HashSet<>();
            //for (SimpleGrantedAuthority authority : grantedAuthorityList) {
            //    if (!StrUtil.equals(authority.getAuthority(), "ROLE_USER")) {
            //        Set<MenuVO> menuVOSet = menuService.findMenuByRole(authority.getAuthority());
            //        if (CollUtil.isNotEmpty(menuVOSet)) {
            //            CollUtil.addAll(urls, menuVOSet);
            //        }
            //    }
            //}

            //for (MenuVO menu : urls) {
            //    if (StringUtils.isNotEmpty(menu.getUrl()) && antPathMatcher.match(menu.getUrl(), request.getRequestURI())
            //            && request.getMethod().equalsIgnoreCase(menu.getMethod())) {
            //        hasPermission = true;
            //        break;
            //    }
            //}
        }
        return hasPermission;
    }
}
