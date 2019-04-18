package com.zzc.election_server.controller;

import com.alibaba.fastjson.JSON;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ExtActivityUser;
import com.zzc.election_server.service.ActivityUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caopengflying
 * @time 2019/1/23 17:05
 */
@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActivityUserController {
    @Resource
    private ActivityUserService activityUserService;

    @RequestMapping("get")
    public Result get(String jsonObject){
        ExtActivityUser extUser = JSON.parseObject(jsonObject, ExtActivityUser.class);
        Result result = activityUserService.get(extUser);
        return result;
    }
    @RequestMapping("update")
    public Result update(String jsonObject){
        ExtActivityUser extUser = JSON.parseObject(jsonObject, ExtActivityUser.class);
        Result result = activityUserService.update(extUser);
        return result;
    }
}
