package com.silence.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author silence
 * @Description 修改课程参数
 * @Date 2023/3/27
 */
@Data
@ApiModel(value="UpdateCourseDTO", description="修改课程基本信息")
public class UpdateCourseDTO extends AddCourseDTO {

    @ApiModelProperty(value="课程id", required = true)
    private Long id;

}
