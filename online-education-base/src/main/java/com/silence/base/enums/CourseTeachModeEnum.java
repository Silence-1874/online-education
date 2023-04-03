package com.silence.base.enums;

/**
 * @Author silence
 * @Description 课程教学方式
 * @Date 2023/4/3
 */
public enum CourseTeachModeEnum {

    RECORD("200002", "录播"),
    LIVE("200003", "直播");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    CourseTeachModeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
