package com.zzc.election_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.mapper.GradeMapper;
import com.zzc.election_server.mapper.StudentMapper;
import com.zzc.election_server.model.Grade;
import com.zzc.election_server.model.Student;
import com.zzc.election_server.modelExtend.ExtStudent;
import com.zzc.election_server.service.StudentService;
import com.zzc.election_server.util.ModelTransformUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author caopengflying
 * @time 2019/1/30 18:02
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private GradeMapper gradeMapper;

    @Override
    public Result create(ExtStudent extStudent) {
        try {
            studentMapper.insert(ModelTransformUtils.exchangeClass(extStudent, Student.class));
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "插入失败");
        }
        return ErrorConstant.getSuccessResult("插入成功");
    }

    @Override
    public Result delete(ExtStudent extStudent) {
        if (null == extStudent.getStudentId()) {
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "学生编号不能为空");
        }
        try {
            studentMapper.deleteByPrimaryKey(extStudent.getStudentId());
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "删除失败");
        }
        return ErrorConstant.getSuccessResult("删除成功");
    }

    @Override
    public Result update(ExtStudent extStudent) {

        try {
            studentMapper.updateByPrimaryKeySelective(extStudent);
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "插入失败");
        }
        return ErrorConstant.getSuccessResult("插入成功");
    }

    @Override
    public Result list(ExtStudent extStudent) {
        Result result = new Result();
        List<Student> students = null;
        Map map = Maps.newHashMap();
        try {
            if (null != extStudent.getOffset() && null != extStudent.getLimit()) {
                PageHelper.startPage(extStudent.getOffset(), extStudent.getLimit());
            }
            Example example = new Example(Student.class);
            Example.Criteria criteria = example.createCriteria();
            if (null != extStudent.getGradeId()) {
                criteria.andEqualTo("gradeId", extStudent.getGradeId());
            }
            students = studentMapper.selectByExample(example);
            List<ExtStudent> list = ModelTransformUtils.exchangeClassList(students, ExtStudent.class);
            if (!CollectionUtils.isEmpty(students)) {
                List<Integer> gradeIds =
                        students.stream().map(Student::getGradeId).distinct().collect(Collectors.toList());
                example = new Example(Grade.class);
                example.createCriteria().andIn("gradeId", gradeIds);
                List<Grade> grades = gradeMapper.selectByExample(example);
                if (gradeIds.size()!= grades.size()){
                    return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "数据异常，请联系管理员！");
                }
                Map<Integer, String> gradeMap = grades.stream().collect(Collectors.toMap(Grade::getGradeId,
                        Grade::getGradeName));
                for (ExtStudent student : list) {
                    student.setGradeName(gradeMap.get(student.getGradeId()));
                }
            }
            if (null != extStudent.getOffset() && null != extStudent.getLimit()) {
                PageInfo<Student> pageInfo = new PageInfo<>(students);
                map.put("count", pageInfo.getTotal());
                map.put("list", list);
            }else {
                return ErrorConstant.getSuccessResult(list,"获取学生列表成功");

            }
        } catch (Exception e) {
            return ErrorConstant.getErrorException(e, "获取学生异常，请联系管理员！");
        }
        result.setT(map);
        result.setText("获取学生成功");
        return result;
    }

    @Override
    public Result importStudent(ExtStudent extStudent) {
        return null;
    }
}
