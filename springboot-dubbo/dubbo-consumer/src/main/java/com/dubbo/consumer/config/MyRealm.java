package com.dubbo.consumer.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.HashSet;

public class MyRealm extends AuthorizingRealm {
    @Override //授权方法 只有页面出现关于权限标签的时候或者项目中关于授权注解的时候，才可以被使用
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //给用户设置角色，从数据库中取，我没连数据库直接获取
        authorizationInfo.setRoles(new HashSet<String>(Arrays.asList("admin", "user")));
        //给用户设置权限，权限可以在权限表中获取。
        authorizationInfo.setStringPermissions(new HashSet<>(Arrays.asList("select")));

        return authorizationInfo;
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
