package com.silence.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author silence
 * @Description 绑定教学计划与媒资信息提交参数
 * @Date 2023/5/1
 */
@Data
@ApiModel(value = "BindTeachplanMediaDTO", description = "绑定教学计划与媒资信息提交参数")
public class BindTeachplanMediaDTO {

    @ApiModelProperty(value = "媒资文件id", required = true)
    private String mediaId;

    @ApiModelProperty(value = "媒资文件名称", required = true)
    private String fileName;

    @ApiModelProperty(value = "课程计划id", required = true)
    private Long teachplanId;

}
