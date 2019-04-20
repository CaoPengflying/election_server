package com.zzc.election_server.modelExtend;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.model.Activity;
import org.apache.commons.lang3.StringUtils;

/**
 * @author caopengflying
 * @time 2019/1/30 13:34
 */
public class ExtActivity extends Activity {
    private Integer offset;
    private Integer limit;
    private Integer leftVotes;
    private Integer studentId;
    private String createUserName;
    private String createGradeName;

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateGradeName() {
        return createGradeName;
    }

    public void setCreateGradeName(String createGradeName) {
        this.createGradeName = createGradeName;
    }

    public Result checkForGetJoinActivities() {
        StringBuffer errorMsg = new StringBuffer();
        if (null == this.getJoinGrade()){
            errorMsg.append("参与的班级不能为空");
        }
        if (StringUtils.isNotEmpty(errorMsg)){
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, errorMsg.toString());
        }
        return ErrorConstant.getSuccessResult("");
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    public Integer getLeftVotes() {
        return leftVotes;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setLeftVotes(Integer leftVotes) {
        this.leftVotes = leftVotes;
    }

    public Result checkForCreate() {
        StringBuffer errorMsg = new StringBuffer();
        if (null == this.getStartTime()){
            errorMsg.append("起始时间不能为空");
        }
        if (null == this.getEndTime()){
            errorMsg.append("终止时间不能为空");
        }
        if (StringUtils.isNotEmpty(errorMsg)){
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, errorMsg.toString());
        }else {
            if (this.getEndTime().compareTo(this.getStartTime()) < 0){
                return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "终止时间需大于起始时间");
            }
        }
        return ErrorConstant.getSuccessResult("");
    }
}
