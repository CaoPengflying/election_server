package com.zzc.election_server.controller;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.model.Student;
import com.zzc.election_server.service.HelloService;
import com.zzc.election_server.service.HelloService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author caopengflying
 * @time 2019/4/29 17:46
 */
@RestController
@RequestMapping(value = "hello")
public class HelloController {

    @Value("${config:1000}")
    private String config;
    @Autowired
    private HelloService helloService;
    @Autowired
    private HelloService2 helloService2;

    @RequestMapping(value = "hello")
    public Result hello() {
        System.out.println("hello ");
        System.out.println("dev  update again");
        System.out.println("config:" + config);
        return ErrorConstant.getSuccessResult("hello docker");
    }

    @RequestMapping(value = "testTransactional")
    public Result testTransactional() {
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentName("test003");
        helloService.updateStudent(student);
        return ErrorConstant.getSuccessResult("");
    }

    @RequestMapping(value = "testTransactional2")
    public Result testTransactional2() {
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentName("test001");
        helloService2.updateStudent(student);
        return ErrorConstant.getSuccessResult("");
    }
}
