package com.zzc.election_server.modelExtend;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import com.zzc.election_server.model.ActivityUser;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author caopengflying
 * @time 2019/1/23 17:44
 */
@Data
public class ExtActivityUser extends ActivityUser {

    private Integer limit;
    private Integer offset;
    private Boolean selectFlag;
    private String studentName;

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
            return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, errorMsg.toString());
        }
        return ErrorConstant.getSuccessResult("");
    }
}
