package com.silence.base.enums;

/**
 * @Author silence
 * @Description 对象审核状态
 * @Date 2023/4/3
 */
public enum ObjectAuditStatusEnum {

    NOT_PASS("002001", "审核未通过"),
    NOT_AUDIT("002002", "未审核"),
    PASSED("002003", "审核通过");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    ObjectAuditStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
