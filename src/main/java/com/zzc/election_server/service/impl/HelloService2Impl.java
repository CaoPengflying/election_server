package com.zzc.election_server.service.impl;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.mapper.StudentMapper;
import com.zzc.election_server.model.Student;
import com.zzc.election_server.service.HelloService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author caopengflying
 * @time 2019/6/6 16:28
 */
@Service
public class HelloService2Impl implements HelloService2 {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private HelloService2 helloService2;
    @Override
    public Result updateStudent(Student student){
        studentMapper.updateByPrimaryKey(student);
        return ErrorConstant.getSuccessResult("");
    }

    public Result updateStudent(){
        int i = 1/0;
        return ErrorConstant.getSuccessResult("");
    }
}
