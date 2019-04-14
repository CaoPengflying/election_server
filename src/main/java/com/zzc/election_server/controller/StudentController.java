package com.zzc.election_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ExtStudent;
import com.zzc.election_server.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author caopengflying
 * @time 2019/1/23 16:52
 */
@Controller
@RequestMapping(value = "/student", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentController {
    @Resource
    private StudentService studentService;

    /**
     * 登录
     * @param jsonObject
     * @return
     */
    @RequestMapping("/login")
    public Result login(String jsonObject){
        ExtStudent extStudent = JSONObject.parseObject(jsonObject, ExtStudent.class);
        return studentService.create(extStudent);
    }

    /**
     * 增加学生
     * @param jsonObject
     * @return
     */
    @RequestMapping("/create")
    public Result create(String jsonObject){
        ExtStudent extStudent = JSONObject.parseObject(jsonObject, ExtStudent.class);
        return studentService.create(extStudent);
    }
    @RequestMapping("/delete")
    public Result delete(String jsonObject){
        ExtStudent extStudent = JSONObject.parseObject(jsonObject, ExtStudent.class);
        return studentService.delete(extStudent);
    }
    @RequestMapping("/update")
    public Result update(String jsonObject){
        ExtStudent extStudent = JSONObject.parseObject(jsonObject, ExtStudent.class);
        return studentService.update(extStudent);
    }
    @RequestMapping("/list")
    public Result list(String jsonObject){
        ExtStudent extStudent = JSONObject.parseObject(jsonObject, ExtStudent.class);
        return studentService.list(extStudent);
    }
    @RequestMapping("/importStudent")
    public Result importStudent(String jsonObject){
        ExtStudent extStudent = JSONObject.parseObject(jsonObject, ExtStudent.class);
        return studentService.importStudent(extStudent);
    }

}
