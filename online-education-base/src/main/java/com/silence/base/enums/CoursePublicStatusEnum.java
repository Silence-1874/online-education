package com.silence.base.enums;

/**
 * @Author silence
 * @Description 课程发布状态
 * @Date 2023/4/3
 */
public enum CoursePublicStatusEnum {

    NOT_PUBLISH("203001", "未发布"),
    PUBLISHED("203002", "已发布"),
    OFFLINE("203003", "下线");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    CoursePublicStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
