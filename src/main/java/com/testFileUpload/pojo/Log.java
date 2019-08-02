package com.testFileUpload.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "log")
@Data
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    //日志主键
    private int id;
    //日志
    private String logId;
    //日志类型
    private String logType;
    //URL
    private String requestUrl;
    //请求方式
    private String method;
    //提交的参数
    private String params;
    //日志内容
    private String description;
    //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateDate;
    //用户Id
    private String userId;
    //返回参数
    private String resultParams;
}
