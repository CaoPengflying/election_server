package com.zzc.election_server.service.impl;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.model.User;
import com.zzc.election_server.mapper.UserMapper;
import com.zzc.election_server.modelExtend.ExtUser;
import com.zzc.election_server.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author caopengflying
 * @time 2019/1/23 17:12
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public Result get(ExtUser extUser) {
        User user1 = userMapper.selectByPrimaryKey(extUser);
        return ErrorConstant.getSuccessResult(user1,"获取用户成功");
    }
}
