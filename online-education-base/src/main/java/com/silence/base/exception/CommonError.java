package com.silence.base.exception;

/**
 * @Author silence
 * @Description 通用错误信息
 * @Date 2023/3/24
 */
public enum CommonError {
    UNKNOWN_ERROR("执行过程异常，请稍后再试"),
    PARAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空"),
    QUERY_NULL("查询结果为空"),
    REQUEST_NULL("请求参数为空");

    private final String errMessage;

    CommonError(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
