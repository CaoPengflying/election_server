package com.zzc.election_server.controller;

import com.alibaba.fastjson.JSON;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ExtTeacher;
import com.zzc.election_server.service.TeacherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caopengflying
 * @time 2019/1/24 10:14
 */
@RestController
@RequestMapping(value = "/teacher" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    @RequestMapping("/get")
    public Result get(String jsonObject){
        ExtTeacher extTeacher = JSON.parseObject(jsonObject, ExtTeacher.class);
        Result result = teacherService.get(extTeacher);
        return result;
    }
}
