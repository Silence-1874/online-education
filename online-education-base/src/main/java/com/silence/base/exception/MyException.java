package com.silence.base.exception;

/**
 * @Author silence
 * @Description 自定义异常
 * @Date 2023/3/24
 */
public class MyException extends RuntimeException {

    private String errMessage;

    public MyException() {
        super();
    }

    public MyException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast(CommonError commonError){
        throw new MyException(commonError.getErrMessage());
    }

    public static void cast(String errMessage){
        throw new MyException(errMessage);
    }

}
