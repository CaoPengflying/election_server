package com.zzc.election_server.service;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.mapper.StudentMapper;
import com.zzc.election_server.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author caopengflying
 * @time 2019/6/6 16:20
 */
@Service
public class HelloService {
    @Autowired
    private HelloService helloService;
    @Autowired
    private StudentMapper studentMapper;
    public Result updateStudent(Student student){
        helloService.updateStudent1(student);
        return ErrorConstant.getSuccessResult("");
    }
    @Transactional(rollbackFor = Exception.class)
    public Result updateStudent1(Student student){
        studentMapper.updateByPrimaryKey(student);
        int i = 1/0;
        return ErrorConstant.getSuccessResult("");
    }
}
