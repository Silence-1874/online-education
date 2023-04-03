package com.silence.base.enums;

/**
 * @Author silence
 * @Description 课程收费情况
 * @Date 2023/4/3
 */
public enum CourseChargeTypeEnum {

    FREE("201000", "免费"),
    CHARGE("201001", "收费");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    CourseChargeTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
