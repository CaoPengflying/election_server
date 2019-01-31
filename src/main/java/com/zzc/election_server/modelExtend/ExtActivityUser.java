package com.zzc.election_server.modelExtend;

import com.zzc.election_server.model.ActivityUser;

/**
 * @author caopengflying
 * @time 2019/1/23 17:44
 */
public class ExtActivityUser extends ActivityUser {

    private Integer limit;
    private Integer offset;

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
}
