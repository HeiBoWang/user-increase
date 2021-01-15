package com.jd.user.increase.common;

import com.jd.user.increase.enums.ErrorCode;

import java.io.Serializable;

/**
 * 定义http接口返回标准格式
 *
 * @author wangyongpeng11
 * @date 2020-12-24 13:58
 */
public class HttpResult<T> implements Serializable {

    private static final long serialVersionUID = 9190714005539792351L;

    /* 状态码 */
    private Integer code;

    /* 状态说明 */
    private String msg;

    /* 承载数据 */
    private T data;

    public HttpResult(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getDesc();
    }

    public HttpResult(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getDesc();
        this.data = data;
    }

    public HttpResult(Integer errorCode, String errorMsg, Object data) {
        this.code = errorCode;
        this.msg = errorMsg;
        this.data = (T) data;
    }

    public HttpResult() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <R> HttpResult<R> buildSuccessResult(R data) {
        return buildNewResult(ErrorCode.SUCCESS, data);
    }

    public static <R> HttpResult<R> buildErrorResult(ErrorCode errorCode) {
        return buildNewResult(errorCode, null);
    }


    public static <R> HttpResult<R> buildNewResult(ErrorCode errorCode, R data) {
        return new HttpResult(errorCode, data);
    }

    public static <R> HttpResult<R> buildCustomizeErrorResult(Integer errorCode, String errorMsg, R data) {
        return new HttpResult(errorCode, errorMsg, data);
    }


}
