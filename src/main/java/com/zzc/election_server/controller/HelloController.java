package com.zzc.election_server.controller;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caopengflying
 * @time 2019/4/29 17:46
 */
@RestController
@RequestMapping(value = "hello")
public class HelloController {
    @RequestMapping(value = "hello")
    public Result hello(){
        System.out.println("hello ");
        System.out.println("dev update");
        return ErrorConstant.getSuccessResult("");
    }
}
