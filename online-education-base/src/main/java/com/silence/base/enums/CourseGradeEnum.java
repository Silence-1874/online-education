package com.silence.base.enums;

/**
 * @Author silence
 * @Description 课程等级
 * @Date 2023/4/3
 */
public enum CourseGradeEnum {

    PRIMARY("204001", "初级"),
    MEDIUM("204002", "中级"),
    ADVANCE("204003", "高级");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    CourseGradeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
