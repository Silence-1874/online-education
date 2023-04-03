package com.silence.base.enums;

/**
 * @Author silence
 * @Description 公共属性类型
 * @Date 2023/4/3
 */
public enum PublicStatusEnum {

    USING("1", 1, "使用态"),
    DELETE("0", 0, "删除态"),
    TEMPORARY("-1", -1, "暂时态");

    private String code;
    private Integer codeInt;
    private String desc;

    public String getCode() {
        return code;
    }

    public Integer getCodeInt() {
        return codeInt;
    }

    PublicStatusEnum(String code, Integer codeInt, String desc) {
        this.code = code;
        this.codeInt = codeInt;
        this.desc = desc;
    }

}
