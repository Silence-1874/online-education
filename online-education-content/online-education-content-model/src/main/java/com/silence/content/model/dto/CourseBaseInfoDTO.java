package com.silence.content.model.dto;

import com.silence.content.model.po.CourseBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author silence
 * @Description 课程基本信息
 * @Date 2023/3/24
 */
@Data
@ApiModel(value="CourseBaseInfoDTO", description="课程基本信息")
public class CourseBaseInfoDTO extends CourseBase {

    @ApiModelProperty("收费规则，对应数据字典")
    private String charge;

    @ApiModelProperty("价格")
    private Float price;

    @ApiModelProperty("原价")
    private Float originalPrice;

    @ApiModelProperty("咨询qq")
    private String qq;

    @ApiModelProperty("微信")
    private String wechat;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("有效期天数")
    private Integer validDays;

    @ApiModelProperty("大分类名称")
    private String mtName;

    @ApiModelProperty("小分类名称")
    private String stName;

}
