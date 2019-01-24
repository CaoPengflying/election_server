package com.zzc.election_server.common;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author caopengflying
 * @time 2019/1/23 17:15
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -3951187350403816092L;

    public static interface OnlyHintView {

    }

    public static interface OnlyObjectView {

    }

    public static interface AllView extends OnlyHintView, OnlyObjectView {

    }

    @JsonView({ OnlyHintView.class, AllView.class })
    public boolean isSuccess() {
        return StringUtils.equals(getStatus(), StatusCode.SUCCESS.value());
    }

    /**
     * 状态
     */
    @JsonView({ OnlyHintView.class, AllView.class })
    private String status;

    /**
     * 状态文本
     */
    @JsonView({ OnlyHintView.class, AllView.class })
    private String text;

    /**
     * 描述
     */
    @JsonView({ OnlyHintView.class, AllView.class })
    private String description;

    /**
     * 内容
     */
    @JsonView({ OnlyObjectView.class, AllView.class })
    private T t;

    public Result() {
        this.status = StatusCode.SUCCESS.value();
        this.text = StatusCode.SUCCESS.name();
        this.description=StatusCode.SUCCESS.name();
    }
    public Result(BusinessException businessException) {
        this.status = businessException.getCode();
        this.text = businessException.getMessage();
    }

    public Result(T t) {
        this.status = StatusCode.SUCCESS.value();
        this.text = StatusCode.SUCCESS.name();
        this.t = t;
    }

    public Result(StatusCode statusCode, String description) {
        this.status = statusCode.value();
        this.text = statusCode.name();
        this.description = description;
    }

    public Result(String status, String text, String description) {
        this.status = status;
        this.text = text;
        this.description = description;
    }

    public Result(String status, String text, T t) {
        this.status = status;
        this.text = text;
        this.t = t;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "{" +
                "status:'" + status + '\'' +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                ", t=" + t +
                '}';
    }
}
