package com.zzc.election_server.modelExtend;

import com.zzc.election_server.model.Student;

/**
 * @author caopengflying
 * @time 2019/1/30 13:35
 */
public class ExtStudent extends Student {
    private Integer offset;
    private Integer limit;

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
}
