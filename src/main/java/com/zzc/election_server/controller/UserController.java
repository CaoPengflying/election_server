package com.zzc.election_server.controller;

import com.alibaba.fastjson.JSON;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.model.User;
import com.zzc.election_server.modelExtend.ExtUser;
import com.zzc.election_server.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caopengflying
 * @time 2019/1/23 17:05
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/get")
    public Result get(String jsonObject){
        ExtUser extUser = JSON.parseObject(jsonObject, ExtUser.class);
        Result result = userService.get(extUser);
        return result;
    }
}
