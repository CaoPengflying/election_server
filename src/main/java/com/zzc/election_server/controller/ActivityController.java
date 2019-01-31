package com.zzc.election_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ActivityForm;
import com.zzc.election_server.modelExtend.ExtActivity;
import com.zzc.election_server.service.ActivityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caopengflying
 * @time 2019/1/30 13:38
 */
@RestController
@RequestMapping(value = "activity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActivityController {
    @Resource
    private ActivityService activityService;
    @RequestMapping("get")
    public Result get(String jsonObject){
        ExtActivity extActivity = JSONObject.parseObject(jsonObject, ExtActivity.class);
        return activityService.get(extActivity);
    }

    @RequestMapping("create")
    public Result create(String jsonObject){
        ActivityForm activityForm = JSONObject.parseObject(jsonObject, ActivityForm.class);
        return activityService.create(activityForm);
    }
    @RequestMapping("getJoinActiveActivity")
    public Result getJoinActiveActivity(String jsonObject){
        ExtActivity extActivity = JSONObject.parseObject(jsonObject, ExtActivity.class);
        return activityService.getJoinActiveActivity(extActivity);
    }
    @RequestMapping("list")
    public Result list(String jsonObject){
        ExtActivity extActivity = JSONObject.parseObject(jsonObject, ExtActivity.class);
        return activityService.list(extActivity);
    }
    @RequestMapping("update")
    public Result update(String jsonObject){
        ExtActivity extActivity = JSONObject.parseObject(jsonObject, ExtActivity.class);
        return activityService.update(extActivity);
    }
}
