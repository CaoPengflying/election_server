package com.zzc.election_server.service;

import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ExtGrade;
import com.zzc.election_server.modelExtend.ExtStudent;

/**
 * @author caopengflying
 * @time 2019/1/31 10:22
 */
public interface GradeService {
    /**
     * 创建班级
     * @param extGrade
     * @return
     */
    Result create(ExtGrade extGrade);

    /**
     * 删除班级
     * @param extGrade
     * @return
     */
    Result delete(ExtGrade extGrade);

    /**
     *更新班级信息
     * @param extGrade
     * @return
     */
    Result update(ExtGrade extGrade);

    /**
     * 获取班级列表
     * @param extGrade
     * @return
     */
    Result list(ExtGrade extGrade);
}
