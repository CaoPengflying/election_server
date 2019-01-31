package com.zzc.election_server.service;

import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ActivityForm;
import com.zzc.election_server.modelExtend.ExtActivity;

/**
 * @author caopengflying
 * @time 2019/1/31 11:09
 */
public interface ActivityService {
    /**
     *
     * @param extActivity
     * @return
     */
    Result get(ExtActivity extActivity);

    /*8
    新增活动
     */
    Result create(ActivityForm activityForm);

    /**
     * 获取活动列表
     * @param extActivity
     * @return
     */
    Result list(ExtActivity extActivity);

    /**
     *
     * @param extActivity
     * @return
     */
    Result update(ExtActivity extActivity);

    /**
     * 获取该学生参与的评选活动
     * @param extActivity
     * @return
     */
    Result getJoinActiveActivity(ExtActivity extActivity);
}
