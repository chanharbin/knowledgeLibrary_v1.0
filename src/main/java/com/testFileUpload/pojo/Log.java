package com.testFileUpload.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    //日志主键
    private String logId;
    //日志类型
    private String type;
    //日志标题
    private String title;
    //请求地址
    private String remoteAddr;
    //URL
    private String requestUri;
    //请求方式
    private String method;
    //提交的参数
    private String params;
    //异常
    private String exception;
    //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateDate;
    //结束时间
    private Date timeout;
    //用户Id
    private String userId;
    //返回参数
    private String resultParams;

    public String getLogId() {
        return logId;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public String getMethod() {
        return method;
    }

    public String getParams() {
        return params;
    }

    public String getException() {
        return exception;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public Date getTimeout() {
        return timeout;
    }

    public String getUserId() {
        return userId;
    }

    public String getResultParams() {
        return resultParams;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public void setTimeout(Date timeout) {
        this.timeout = timeout;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setResultParams(String resultParams) {
        this.resultParams = resultParams;
    }
}
