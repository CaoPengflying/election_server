package com.zzc.election_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.mapper.GradeMapper;
import com.zzc.election_server.model.Grade;
import com.zzc.election_server.model.Student;
import com.zzc.election_server.modelExtend.ExtGrade;
import com.zzc.election_server.service.GradeService;
import com.zzc.election_server.util.ModelTransformUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author caopengflying
 * @time 2019/1/31 10:22
 */
@Service
public class GradeServiceImpl implements GradeService {
    @Resource
    private GradeMapper gradeMapper;
    @Override
    public Result create(ExtGrade extGrade) {
        try {
            gradeMapper.insert(ModelTransformUtils.exchangeClass(extGrade, Grade.class));
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "增加班级失败");
        }
        return ErrorConstant.getSuccessResult("增加班级成功");
    }

    @Override
    public Result delete(ExtGrade extGrade) {
        if (null == extGrade.getGradeId()){
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL,"班级编号不能为空");
        }
        try {
            gradeMapper.deleteByPrimaryKey(extGrade.getGradeId());
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "删除失败");
        }
        return ErrorConstant.getSuccessResult("删除成功");
    }

    @Override
    public Result update(ExtGrade extGrade) {
        try {
            gradeMapper.updateByPrimaryKeySelective(extGrade);
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "修改失败");
        }
        return ErrorConstant.getSuccessResult("修改成功");
    }

    @Override
    public Result list(ExtGrade extGrade) {
        Result result = new Result();
        List<Grade> grades = null;
        Map map = Maps.newHashMap();
        try {
            if (null != extGrade.getOffset() && null != extGrade.getLimit()){
                PageHelper.startPage(extGrade.getOffset(), extGrade.getLimit());
            }
            Example example = new Example(Grade.class);
            Example.Criteria criteria = example.createCriteria();
            if (null != extGrade.getGradeId()){
                criteria.andEqualTo("gradeId",extGrade.getGradeId());
            }
            grades = gradeMapper.selectByExample(example);
            PageInfo<Grade> pageInfo = new PageInfo<>(grades);
            map.put("list", grades);
            map.put("count",pageInfo.getTotal());
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "获取学生异常，请联系管理员！");
        }
        result.setT(map);
        result.setText("获取学生成功");
        return result;
    }
}
