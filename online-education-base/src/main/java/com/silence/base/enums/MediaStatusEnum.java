package com.silence.base.enums;

/**
 * @Author silence
 * @Description 视频处理状态
 * @Date 2023/4/11
 */
public enum MediaStatusEnum {

    NOT_PROCESS("1", "未处理"),
    PROCESSED("2",  "处理完成");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

    MediaStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
