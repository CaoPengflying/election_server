package com.zzc.election_server.modelExtend;

import com.zzc.election_server.common.ErrorConstant;
import com.zzc.election_server.common.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author caopengflying
 * @time 2019/1/31 11:16
 */
public class ActivityForm {
    private ExtActivity extActivity;
    private List<ExtActivityUser> extActivityUserList;

    public ExtActivity getExtActivity() {
        return extActivity;
    }

    public void setExtActivity(ExtActivity extActivity) {
        this.extActivity = extActivity;
    }

    public List<ExtActivityUser> getExtActivityUserList() {
        return extActivityUserList;
    }

    public void setExtActivityUserList(List<ExtActivityUser> extActivityUserList) {
        this.extActivityUserList = extActivityUserList;
    }

    public Result checkForCreate() {
        StringBuffer errorMsg = new StringBuffer();
        if (null == this.getExtActivity()){

            errorMsg.append("[活动不能为空]");
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
