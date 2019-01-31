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
import com.zzc.election_server.modelExtend.ActivityForm;
import com.zzc.election_server.modelExtend.ExtActivity;
import com.zzc.election_server.modelExtend.ExtActivityUser;
import com.zzc.election_server.service.ActivityService;
import com.zzc.election_server.util.ModelTransformUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author caopengflying
 * @time 2019/1/31 11:09
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private ActivityUserMapper activityUserMapper;
    @Resource
    private ActivityUserSelectMapper activityUserSelectMapper;
    @Override
    public Result get(ExtActivity extActivity) {
        ActivityForm activityForm = new ActivityForm();
        try {
            if (null == extActivity.getActivityId()){
                return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "活动编号不能为空");
            }
            Activity activity = activityMapper.selectByPrimaryKey(extActivity.getActivityId());
            if (null == activity){
                return ErrorConstant.getErrorResult(ErrorConstant.DATA_NOT_EXISTS, "该活动不存在");
            }
            Example example = new Example(ActivityUser.class);
            example.createCriteria().andEqualTo("activityId", activity.getActivityId());
            List<ActivityUser> activityUsers = activityUserMapper.selectByExample(example);
            activityForm.setExtActivity(ModelTransformUtils.exchangeClass(activity, ExtActivity.class));
            List<ExtActivityUser> extActivityUsers = ModelTransformUtils.exchangeClassList(activityUsers, ExtActivityUser.class);
            extActivityUsers.forEach(extActivityUser -> {
                Example e = new Example(ActivityUserSelect.class);
                e.createCriteria()
                        .andEqualTo("userId",extActivityUser.getUserId())
                        .andEqualTo("studentId", extActivityUser.getStudentId());
                List<ActivityUserSelect> activityUserSelects = activityUserSelectMapper.selectByExample(e);
                if (!CollectionUtils.isEmpty(activityUserSelects)){
                    extActivityUser.setSelectFlag(true);
                }else {
                    extActivityUser.setSelectFlag(false);
                }
            });
            activityForm.setExtActivityUserList(extActivityUsers);
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "获取活动失败");
        }
        return ErrorConstant.getSuccessResult(activityForm, "获取活动详情成功");
    }

    @Override
    public Result create(ActivityForm activityForm) {
        Result result = activityForm.checkForCreate();
        if (ErrorConstant.checkSuccess(result)){
            return result;
        }
        Activity activity = null;
        try {
            activity = ModelTransformUtils.exchangeClass(activityForm.getExtActivity(), Activity.class);
            activityMapper.insert(activity);
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "增加活动失败");
        }
        for (ExtActivityUser extActivityUser : activityForm.getExtActivityUserList()) {
            extActivityUser.setActivityId(activity.getActivityId());
        }
        try {
            activityUserMapper.insertList(ModelTransformUtils.exchangeClassList(activityForm.getExtActivityUserList(),
                    ActivityUser.class));
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ErrorConstant.getErrorException(e, "插入活动参选人员失败");
        }
        return ErrorConstant.getSuccessResult("增加活动成功");
    }

    @Override
    public Result list(ExtActivity extActivity) {
        Result result = new Result();
        List<Activity> activities = null;
        Map map = Maps.newHashMap();
        try {
            if (null != extActivity.getOffset() && null != extActivity.getLimit()){
                PageHelper.startPage(extActivity.getOffset(), extActivity.getLimit());
            }
            Example example = new Example(Activity.class);
            Example.Criteria criteria = example.createCriteria();
            activities = activityMapper.selectByExample(example);
            PageInfo<Activity> pageInfo = new PageInfo<>(activities);
            map.put("list", activities);
            map.put("count",pageInfo.getTotal());
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "获取学生异常，请联系管理员！");
        }
        result.setT(map);
        result.setText("获取学生成功");
        return result;
    }

    @Override
    public Result update(ExtActivity extActivity) {
        if (null == extActivity.getActivityId()){
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "活动不存在");
        }
        Activity activity = activityMapper.selectByPrimaryKey(extActivity.getActivityId());
        if (null == activity){
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "活动不存在");
        }
        if (activity.getStartTime().compareTo(new Date()) < 0){
            if(activity.getEndTime().compareTo(new Date()) > 0){
                return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "活动已开始");
            }else {
                return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "活动已结束");
            }
        }
        try {
            activityMapper.updateByPrimaryKeySelective(extActivity);
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "修改失败");
        }
        return ErrorConstant.getSuccessResult("修改成功");
    }

    @Override
    public Result getJoinActiveActivity(ExtActivity extActivity) {
        Result result = extActivity.checkForGetJoinActivities();
        if (ErrorConstant.checkSuccess(result)){
            return result;
        }
        List<Activity> activities = null;
        try {
            Example example = new Example(Activity.class);
            example.createCriteria()
                    .andEqualTo("joinGrade",extActivity.getJoinGarde())
                    .andGreaterThanOrEqualTo("startTime",new Date())
                    .andLessThanOrEqualTo("endTime",new Date());
            activities = activityMapper.selectByExample(example);
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "获取活动失败");
        }
        result.setT(ModelTransformUtils.exchangeClassList(activities, ExtActivity.class));
        result.setText("获取活动成功");
        return result;
    }


}
