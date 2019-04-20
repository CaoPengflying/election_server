package com.zzc.election_server.modelExtend;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.model.ActivityUser;
import com.zzc.election_server.model.ActivityUserSelect;
import org.apache.commons.lang3.StringUtils;

/**
 * @author caopengflying
 * @time 2019/1/23 17:44
 */
public class ExtActivityUser extends ActivityUser {

    private Integer limit;
    private Integer offset;
    private Boolean selectFlag;


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }



    public Boolean getSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(Boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

    public Result checkForUpdate() {
        StringBuffer errorMsg = new StringBuffer();
        if (null == this.getStudentId()){
            errorMsg.append("[请传入学生id]");
        }
        if (null == this.getUserId()){
            errorMsg.append("[活动参选人员id不能为空]");
        }
        if (null == this.getVote()){
            errorMsg.append("[票数不能为空]");
        }
        if (StringUtils.isNotEmpty(errorMsg)){
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "数据不能为空");
        }
        return ErrorConstant.getSuccessResult("");
    }
}
