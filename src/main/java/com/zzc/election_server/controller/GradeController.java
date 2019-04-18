package com.zzc.election_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ExtGrade;
import com.zzc.election_server.modelExtend.ExtStudent;
import com.zzc.election_server.service.GradeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caopengflying
 * @time 2019/1/30 13:38
 */
@RestController
@RequestMapping(value = "grade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GradeController {
    @Resource
    private GradeService gradeService;

    @RequestMapping(value = "create")
    public Result create(String jsonObject){
        ExtGrade extGrade = JSONObject.parseObject(jsonObject, ExtGrade.class);
        return gradeService.create(extGrade);
    }
    @RequestMapping("delete")
    public Result delete(String jsonObject){
        ExtGrade extGrade = JSONObject.parseObject(jsonObject, ExtGrade.class);
        return gradeService.delete(extGrade);
    }
    @RequestMapping("update")
    public Result update(String jsonObject){
        ExtGrade extGrade = JSONObject.parseObject(jsonObject, ExtGrade.class);
        return gradeService.update(extGrade);
    }
    @RequestMapping("list")
    public Result list(String jsonObject){
        ExtGrade extGrade = JSONObject.parseObject(jsonObject, ExtGrade.class);
        return gradeService.list(extGrade);
    }
}
