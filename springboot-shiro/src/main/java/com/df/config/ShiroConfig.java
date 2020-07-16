package com.df.config;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    MyRealm myRealm(){
        return new MyRealm();
    }

    @Bean
    DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        MyRealm myRealm = myRealm();
        myRealm.setCredentialsMatcher(myCredentialsMatcher());
        manager.setRealm(myRealm);
        manager.setSessionManager(getDefaultWebSessionManager());

        return manager;
    }

    @Bean
    public MyCredentialsMatcher myCredentialsMatcher(){
        return new MyCredentialsMatcher();
    }

    //设置session过期时间
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1000 * 60);// 会话过期时间，单位：毫秒--->一分钟,用于测试
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        //设置cookie有效期
        defaultWebSessionManager.getSessionIdCookie();
        return defaultWebSessionManager;
    }




    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //传入安全管理器
        bean.setSecurityManager(securityManager());
        //传入未登录用户访问登陆用户的权限所跳转的页面
        bean.setLoginUrl("/login");

        //设置成功后返回页面
        bean.setSuccessUrl("/index");

        //访问未授权网页所跳转的页面
        bean.setUnauthorizedUrl("/unauthorized");
        Map<String, String> map = new LinkedHashMap<>();
        //允许  需要设置login为anon 否则登陆成功后无法成功跳转。
        map.put("/login", "anon");
        map.put("/doLogin", "anon");
        map.put("/index", "anon");

        //身份权限认证
        //访问/admin/visit开头的用户需要是admin角色
        map.put("/admin/visit*/**", "roles[admin,user]");
        //访问/admin/select开头的用户需要是admin:select才允许
        map.put("/admin/select*/**", "perms[select]");

        //不允许
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

}
