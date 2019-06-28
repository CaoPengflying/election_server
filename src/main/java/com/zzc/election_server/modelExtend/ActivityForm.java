package com.zzc.election_server.modelExtend;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author caopengflying
 * @time 2019/1/31 11:16
 */
@Data
public class ActivityForm {
    private ExtActivity extActivity;
    private List<ExtActivityUser> extActivityUserList;

    public Result checkForCreate() {
        StringBuffer errorMsg = new StringBuffer();
        if (null == this.getExtActivity()){

            errorMsg.append("[活动不能为空]");
        }else {
            this.getExtActivity().checkForCreate();
        }
        if (CollectionUtils.isEmpty(this.getExtActivityUserList())){
            errorMsg.append("[参选人员不能为空]");
        }
        if (StringUtils.isNotEmpty(errorMsg)){
            return ErrorConstant.getErrorResult(ErrorConstant.FAIL, errorMsg.toString());
        }
        return ErrorConstant.getSuccessResult("");

    }
}
