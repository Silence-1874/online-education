package com.silence.base.enums;

/**
 * @Author silence
 * @Description 资源类型
 * @Date 2023/4/3
 */
public enum MediaTypeEnum {

    IMAGE("001001", "图片"),
    VIDEO("001002", "视频"),
    OTHER("001003", "其他");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    MediaTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
