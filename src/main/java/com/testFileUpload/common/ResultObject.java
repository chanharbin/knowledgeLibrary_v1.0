package com.testFileUpload.common;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * 返回结果类
 *
 * @author CAIFUCHENG3
 */
public class ResultObject<T> {
    /**
     * 成功代码
     */
    public static final String SUCCESS = "S1A00000";
    /**
     * 失败代码
     */
    public static final String FAILURE = "E0B00001";
    /**
     * 返回结果代码
     */
    @ApiModelProperty(value = "返回结果代码", example = "S1A00000：成功；E0B00001：失败；")
    private String code;
    /**
     * 返回结果
     */
    @ApiModelProperty(value = "返回结果提示消息", example = "成功提示消息或异常提示消息")
    private String msg;

    @ApiModelProperty(value = "返回结果", example = "不同业务返回字段不同，可能为空")
    private T data;


    /**
     * 返回错误异常堆栈(原始异常)
     */
    @ApiModelProperty(value = "返回错误异常堆栈", example = "返回错误异常堆栈")
    private String error;

    /**
     * 构造函数
     */
    public ResultObject() {
        this(SUCCESS, "");
    }

    /**
     * 构造函数，执行成功
     *
     * @param msg
     *            成功结果
     */
    public ResultObject(String msg) {
        this(SUCCESS, msg);
    }


    public ResultObject(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }



    /**
     * 构造函数
     *
     * @param code
     *            成功或失败代码
     * @param msg
     *            成功或失败结果
     * @param data
     *            结果集
     */
    public ResultObject(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static ResultObject makeSuccess() {
        return new ResultObject();
    }

    /**
     * 封装只有msg返回的成功查询
     * @param msg
     * @return
     */
    public static ResultObject makeSuccess(String msg) {
        return new ResultObject(msg);
    }

    /**
     * 封装含对象和msg的成功返回查询
     * @param data
     * @param msg
     * @param <R>
     * @return
     */
    public static<R> ResultObject<R> makeSuccess(R data,String msg){
        return new ResultObject(SUCCESS,msg,data);
    }


    /**
     * 只有失败信息的查询返回
     * @param msg
     * @return
     */
    public static ResultObject makeFail(String msg) {
        return new ResultObject(FAILURE, msg);
    }

    /**
     * 含返回对象和失败信息的查询返回
     * @param data
     * @param msg
     * @param <R>
     * @return
     */
    public static<R> ResultObject<R> makeFail(R data,String msg){
        return new ResultObject(FAILURE,msg,data);
    }

    public Boolean isSuccess() {
        return Objects.equals(SUCCESS, this.code);
    }

    public Boolean isFail() {
        return !isSuccess();
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }


    public Object getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "code="+code+",msg= "+msg+",error "+error;
    }
}
