package com.zzc.election_server.service.impl;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.mapper.TeacherMapper;
import com.zzc.election_server.model.Teacher;
import com.zzc.election_server.modelExtend.ExtTeacher;
import com.zzc.election_server.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author caopengflying
 * @time 2019/1/24 10:01
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public Result get(ExtTeacher extTeacher) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(extTeacher.getTeacherId());
        Result result = ErrorConstant.getSuccessResult(teacher, "获取老师成功");
        return result;
    }

    @Override
    public Result create(ExtTeacher extTeacher) {
        return null;
    }

    @Override
    public Result delete(ExtTeacher extTeacher) {
        return null;
    }

    @Override
    public Result update(ExtTeacher extTeacher) {
        return null;
    }
}
