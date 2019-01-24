package com.zzc.election_server.service;

import com.zzc.election_server.common.Result;
import com.zzc.election_server.modelExtend.ExtUser;

/**
 * @author caopengflying
 * @time 2019/1/23 17:11
 */
public interface UserService {
    /**
     * 获取用户
     * @param extUser
     * @return
     */
    Result get(ExtUser extUser);
}
