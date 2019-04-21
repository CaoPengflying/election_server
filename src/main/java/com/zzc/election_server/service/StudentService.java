package com.zzc.election_server.service;

import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ExtStudent;

/**
 * @author caopengflying
 * @time 2019/1/30 18:01
 */
public interface StudentService {
    /**
     * 增加学生
     * @param extStudent
     * @return
     */
    Result create(ExtStudent extStudent);

    /**
     * 删除学生
     * @param extStudent
     * @return
     */
    Result delete(ExtStudent extStudent);

    /**
     *
     * @param extStudent
     * @return
     */
    Result update(ExtStudent extStudent);

    /**
     * 获取学生列表
     * @param extStudent
     * @return
     */
    Result list(ExtStudent extStudent);

    /**
     * 导入学生
     * @param extStudent
     * @return
     */
    Result importStudent(ExtStudent extStudent);

    /**
     * 登录
     */
    Result login(ExtStudent extStudent);
}
