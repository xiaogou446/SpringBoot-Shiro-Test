package com.df.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class MyCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken tokenResolve = (UsernamePasswordToken) token;
        String tokenPwd = new String(tokenResolve.getPassword());
        String infoPwd =(String) info.getCredentials();

        return super.equals(tokenPwd, infoPwd);
    }
}
