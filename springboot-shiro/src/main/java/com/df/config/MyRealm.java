package com.df.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author Lin
 * @create 2020/7/15
 * @since 1.0.0
 * (功能)：
 */
public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        // 此处获取到name后可以先去redis中获取实体。
        //如果获取不到再去数据库中查询。
        if(!"LinJy".equals(username)){
            throw new UnknownAccountException("账号不存在");
        }
        return new SimpleAuthenticationInfo(username, "123", this.getName());
    }
}
