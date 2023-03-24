package com.silence.content.model.dto;

import com.silence.content.model.po.CourseBase;
import lombok.Data;

/**
 * @Author silence
 * @Description 课程基本信息
 * @Date 2023/3/24
 */
@Data
public class CourseBaseInfoDTO extends CourseBase {

    /**
     * 收费规则，对应数据字典
     */
    private String charge;

    /**
     * 价格
     */
    private Float price;


    /**
     * 原价
     */
    private Float originalPrice;

    /**
     * 咨询qq
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 电话
     */
    private String phone;

    /**
     * 有效期天数
     */
    private Integer validDays;

    /**
     * 大分类名称
     */
    private String mtName;

    /**
     * 小分类名称
     */
    private String stName;

}
