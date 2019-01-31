package com.zzc.election_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.mapper.ActivityUserMapper;
import com.zzc.election_server.model.ActivityUser;
import com.zzc.election_server.model.Grade;
import com.zzc.election_server.modelExtend.ExtActivityUser;
import com.zzc.election_server.service.ActivityUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author caopengflying
 * @time 2019/1/23 17:12
 */
@Service
public class ActivityUserServiceImpl implements ActivityUserService {
    @Resource
    private ActivityUserMapper activityUserMapper;
    @Override
    public Result get(ExtActivityUser extUser) {
        ActivityUser activityUser = null;
        try {
            activityUser = activityUserMapper.selectByPrimaryKey(extUser.getUserId());
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e,"获取用户成功");
        }
        return ErrorConstant.getSuccessResult(activityUser,"获取用户成功");
    }

    @Override
    public Result update(ExtActivityUser extUser) {
        try {
            activityUserMapper.updateByPrimaryKeySelective(extUser);
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "修改失败");
        }
        return ErrorConstant.getSuccessResult("修改成功");
    }

    @Override
    public Result delete(ExtActivityUser extUser) {
        if (null == extUser.getUserId()){
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL,"班级编号不能为空");
        }
        try {
            activityUserMapper.deleteByPrimaryKey(extUser.getUserId());
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "删除失败");
        }
        return ErrorConstant.getSuccessResult("删除成功");
    }

    @Override
    public Result list(ExtActivityUser extUser) {
        Result result = new Result();
        List<ActivityUser> activityUsers = null;
        Map map = Maps.newHashMap();
        try {
            if (null != extUser.getOffset() && null != extUser.getLimit()){
                PageHelper.startPage(extUser.getOffset(), extUser.getLimit());
            }
            Example example = new Example(Grade.class);
            Example.Criteria criteria = example.createCriteria();
            if (null != extUser.getActivityId()){
                criteria.andEqualTo("gradeId",extUser.getActivityId());
            }
            activityUsers = activityUserMapper.selectByExample(example);
            PageInfo<ActivityUser> pageInfo = new PageInfo<>(activityUsers);
            map.put("list", activityUsers);
            map.put("count",pageInfo.getTotal());
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "获取学生异常，请联系管理员！");
        }
        result.setT(map);
        result.setText("获取学生成功");
        return result;
    }
}
