package com.silence.base.enums;

/**
 * @Author silence
 * @Description 课程审核状态
 * @Date 2023/4/3
 */
public enum CourseAuditStatusEnum {

    NOT_PASS("202001", "审核未通过"),
    NOT_COMMIT("202002", "未提交"),
    COMMITTED("202003", "已提交"),
    PASSED("202004", "审核通过");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    CourseAuditStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
