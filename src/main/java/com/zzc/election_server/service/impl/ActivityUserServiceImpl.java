package com.zzc.election_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.mapper.ActivityMapper;
import com.zzc.election_server.mapper.ActivityUserMapper;
import com.zzc.election_server.mapper.ActivityUserSelectMapper;
import com.zzc.election_server.model.Activity;
import com.zzc.election_server.model.ActivityUser;
import com.zzc.election_server.model.ActivityUserSelect;
import com.zzc.election_server.model.Grade;
import com.zzc.election_server.modelExtend.ExtActivityUser;
import com.zzc.election_server.service.ActivityUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
    @Resource
    private ActivityUserSelectMapper activityUserSelectMapper;
    @Resource
    private ActivityMapper activityMapper;

    @Override
    public Result get(ExtActivityUser extUser) {
        ActivityUser activityUser = null;
        try {
            activityUser = activityUserMapper.selectByPrimaryKey(extUser.getUserId());
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "获取用户成功");
        }
        return ErrorConstant.getSuccessResult(activityUser, "获取用户成功");
    }

    @Override
    public Result update(ExtActivityUser extUser) {
        Result result = extUser.checkForUpdate();
        if (ErrorConstant.checkSuccess(result)) {
            return result;
        }
        try {
            ActivityUser activityUser = activityUserMapper.selectByPrimaryKey(extUser.getUserId());
            if (null == activityUser) {
                return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "该活动人员不存在");
            }
            Activity activity = activityMapper.selectByPrimaryKey(activityUser.getActivityId());
            Example example = new Example(ActivityUserSelect.class);
            example.createCriteria().andEqualTo("userId", extUser.getUserId())
                    .andEqualTo("studentId", extUser.getStudentBId());
            List<ActivityUserSelect> activityUserSelects = activityUserSelectMapper.selectByExample(example);
            example.createCriteria().andEqualTo("studentId",extUser.getStudentId()).andEqualTo("activityId",
                    activity.getActivityId());
            List<ActivityUserSelect> activityUserSelectsSize = activityUserSelectMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(activityUserSelects) && activityUserSelectsSize.size() < activity.getVotes()) {
                activityUserMapper.updateByPrimaryKeySelective(extUser);
                ActivityUserSelect activityUserSelect = new ActivityUserSelect();
                activityUserSelect.setUserId(extUser.getUserId());
                activityUserSelect.setStudentId(extUser.getStudentId());
                activityUserSelectMapper.insert(activityUserSelect);
            } else {
                if (!CollectionUtils.isEmpty(activityUserSelects)){
                    return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "不允许重复选择同一人");
                }else {
                    return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "票数已用完");
                }
            }
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "修改失败");
        }
        return ErrorConstant.getSuccessResult("选票成功");
    }

    @Override
    public Result delete(ExtActivityUser extUser) {
        if (null == extUser.getUserId()) {
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "班级编号不能为空");
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
            if (null != extUser.getOffset() && null != extUser.getLimit()) {
                PageHelper.startPage(extUser.getOffset(), extUser.getLimit());
            }
            Example example = new Example(Grade.class);
            Example.Criteria criteria = example.createCriteria();
            if (null != extUser.getActivityId()) {
                criteria.andEqualTo("gradeId", extUser.getActivityId());
            }
            activityUsers = activityUserMapper.selectByExample(example);
            PageInfo<ActivityUser> pageInfo = new PageInfo<>(activityUsers);
            map.put("list", activityUsers);
            map.put("count", pageInfo.getTotal());
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "获取学生异常，请联系管理员！");
        }
        result.setT(map);
        result.setText("获取学生成功");
        return result;
    }
}
