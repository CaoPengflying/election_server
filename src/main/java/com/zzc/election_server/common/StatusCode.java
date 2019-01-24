package com.zzc.election_server.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author caopengflying
 * @time 2019/1/23 17:18
 */
public enum StatusCode {
    SUCCESS("00000"),
    REQUEST_METHOD_NOT_SUPPORTED("请求方法不支持"),
    PARAM_FIELD_ERROR("参数错误"),
    PARAM_MISSING_FIELD("参数缺失"),
    RESUBMIT("重复提交"),
    BIZ_MISTAKE("业务错误"),
    ASSERT_ERROR("业务断言错误");

    private final String value;

    StatusCode(String value) {
        this.value = value;
    }

    public static StatusCode nameOf(String statusCode) {
        for (StatusCode status : values()) {
            if (StringUtils.equals(status.name(), statusCode)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\", \"value\"=\"");
        sb.append(value);
        sb.append("\"}");
        return sb.toString();
    }
}
