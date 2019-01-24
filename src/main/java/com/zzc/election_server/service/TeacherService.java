package com.zzc.election_server.service;

import com.zzc.election_server.common.Result;
import com.zzc.election_server.model.Teacher;
import com.zzc.election_server.modelExtend.ExtTeacher;

/**
 * @author caopengflying
 * @time 2019/1/24 10:01
 */
public interface TeacherService {
    Result get(ExtTeacher extTeacher);
    Result create(ExtTeacher extTeacher);
    Result delete(ExtTeacher extTeacher);
    Result update(ExtTeacher extTeacher);
}
