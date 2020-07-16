package com.dubbo.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class permissionVisit {

    @GetMapping(value = "/admin/visit")
    public String adminVisit(){
        return "管理员成功访问";
    }

    @GetMapping(value = "/admin/select")
    public String adminSelectVisit(){
        return "管理员select权限访问";
    }

    @GetMapping(value = "/unauthorized")
    public String unauthorized(){
        return "您没有访问的权限";
    }

}
