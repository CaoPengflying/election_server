package com.zzc.election_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.mapper.*;
import com.zzc.election_server.model.*;
import com.zzc.election_server.modelExtend.ActivityForm;
import com.zzc.election_server.modelExtend.ExtActivity;
import com.zzc.election_server.modelExtend.ExtActivityUser;
import com.zzc.election_server.service.ActivityService;
import com.zzc.election_server.util.ModelTransformUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private GradeMapper gradeMapper;

    /**
     * 1.通过activityId查询活动
     * 2.若活动存在 查询当前活动参选人
     * 3.通过参选人查找相应的投票者
     * 4.判断当前登陆用户是否在投票者中
     * @param extActivity
     * @return
     */
    @Override
    public Result get(ExtActivity extActivity) {
        ActivityForm activityForm = new ActivityForm();
        try {
            if (null == extActivity.getActivityId()){
                return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "活动编号不能为空");
            }
            //1
            Activity activity = activityMapper.selectByPrimaryKey(extActivity.getActivityId());
            if (null == activity){
                return ErrorConstant.getErrorResult(ErrorConstant.DATA_NOT_EXISTS, "该活动不存在");
            }
            ExtActivity extActivitySelect = ModelTransformUtils.exchangeClass(activity, ExtActivity.class);
            //查询活动创建人姓名
            Student student = studentMapper.selectByPrimaryKey(extActivitySelect.getCreateUser());
            extActivitySelect.setCreateUserName(student.getStudentName());
            //查询创建班级名称
            Grade grade = gradeMapper.selectByPrimaryKey(extActivitySelect.getJoinGrade());
            extActivitySelect.setCreateGradeName(grade.getGradeName());
            activityForm.setExtActivity(extActivitySelect);
            //第二步
            Example example = new Example(ActivityUser.class);
            example.createCriteria().andEqualTo("activityId", activity.getActivityId());
            List<ActivityUser> activityUsers = activityUserMapper.selectByExample(example);
            List<ExtActivityUser> extActivityUsers = ModelTransformUtils.exchangeClassList(activityUsers, ExtActivityUser.class);
            example = new Example(Student.class);
            example.createCriteria().andIn("studentId",
                    extActivityUsers.stream().map(ExtActivityUser::getStudentId).collect(Collectors.toList()));
            List<Student> students = studentMapper.selectByExample(example);
            Map<Integer, String> collect = students.stream().collect(Collectors.toMap(Student::getStudentId, Student::getStudentName));
            extActivityUsers.forEach(extActivityUser -> {
                extActivityUser.setStudentName(collect.get(extActivityUser.getStudentId()));
                Example e = new Example(ActivityUserSelect.class);
                e.createCriteria()
                        .andEqualTo("userId",extActivityUser.getUserId())
                        .andEqualTo("studentId", extActivity.getStudentId());
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
    @Transactional
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
            if (extActivity.getJoinGrade() != null){
                criteria.andEqualTo("joinGrade",extActivity.getJoinGrade());
            }
            activities = activityMapper.selectByExample(example);
            if (null != extActivity.getOffset() && null != extActivity.getLimit()){
                PageInfo<Activity> pageInfo = new PageInfo<>(activities);
                map.put("count",pageInfo.getTotal());
                map.put("list", activities);
                result.setT(map);
                result.setText("获取活动列表成功");
                return result;
            }else {
                return ErrorConstant.getSuccessResult(activities, "获取活动列表成功");
            }

        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "获取学生列表异常，请联系管理员！");
        }
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
                    .andEqualTo("joinGrade",extActivity.getJoinGrade())
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
