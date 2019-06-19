/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CustomRealm.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-11 下午1:47
 : LastModify: 19-6-11 下午1:45
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.shiro.config;

import com.google.common.collect.Sets;
import com.janloong.shiro.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 认证、授权的核心控制类
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/6/11 17:25
 **/
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        //配置角色、权限信息  配合 @RequiresPermissions() @RequiresRoles() 等使用
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(Sets.newHashSet(user.getRole()));
        simpleAuthorizationInfo.setStringPermissions(Sets.newHashSet(user.getPermission()));
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //根据用户名密码进行认证信息
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }
        //校验用户名密码
        User user = new User();
        user.setUsername("janloong");
        user.setPassword("doo");
        user.setUserId("001");
        user.setRole("role");
        user.setPermission("permission");
        if (!user.getUsername().equals(username)) {
            throw new UnknownAccountException("账户不存在");
        } else if (!user.getPassword().equals(password)) {
            throw new AccountException("密码错误");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
