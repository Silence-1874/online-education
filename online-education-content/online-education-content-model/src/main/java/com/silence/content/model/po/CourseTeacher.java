package com.silence.content.model.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程-教师关系表
 * </p>
 *
 * @author silence
 */
@Data
@ApiModel(value = "CourseTeacher", description = "课程师资")
@TableName("course_teacher")
public class CourseTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程标识
     */
    @ApiModelProperty("课程标识")
    private Long courseId;

    /**
     * 教师标识
     */
    @ApiModelProperty("教师标识")
    private String teacherName;

    /**
     * 教师职位
     */
    @ApiModelProperty("教师职位")
    private String position;

    /**
     * 教师简介
     */
    @ApiModelProperty("教师简介")
    private String introduction;

    /**
     * 照片
     */
    @ApiModelProperty("照片")
    private String photograph;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;


}
