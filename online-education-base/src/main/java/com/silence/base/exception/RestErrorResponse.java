package com.silence.base.exception;

/**
 * @Author silence
 * @Description 错误相应统一返回参数
 * @Date 2023/3/24
 */
public class RestErrorResponse {

    private String errMessage;

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public RestErrorResponse(String errMessage) {
        this.errMessage = errMessage;
    }

}
