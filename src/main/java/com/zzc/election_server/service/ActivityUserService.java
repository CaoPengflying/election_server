package com.zzc.election_server.service;

import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ExtActivityUser;

/**
 * @author caopengflying
 * @time 2019/1/23 17:11
 */
public interface ActivityUserService {
    /**
     * 获取活动参选人员
     * @param extUser
     * @return
     */
    Result get(ExtActivityUser extUser);

    /**
     * 更新活动中参选人员
     * @param extUser
     * @return
     */
    Result update(ExtActivityUser extUser);

    /**
     * 删除活动参选人员
     * @param extUser
     * @return
     */
    Result delete(ExtActivityUser extUser);

    /**
     * 更新活动参选人员
     * @param extUser
     * @return
     */
    Result list(ExtActivityUser extUser);
}
