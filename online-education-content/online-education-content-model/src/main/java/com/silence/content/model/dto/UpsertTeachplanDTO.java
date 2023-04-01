package com.silence.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author silence
 * @Description 修改（没有则添加）教学计划DTO
 * @Date 2023/3/31
 */
@Data
@ApiModel(value = "UpsertTeachplanDTO", description = "修改或添加教学计划")
public class UpsertTeachplanDTO {

    @ApiModelProperty("教学计划id")
    private Long id;

    @ApiModelProperty("教学计划名称")
    private String pname;

    @ApiModelProperty("父级教学计划id")
    private Long parentid;

    @ApiModelProperty("层级")
    private Integer grade;

    @ApiModelProperty("课程类型，1-视频，2-文档")
    private String mediaType;

    @ApiModelProperty("课程id")
    private Long courseId;

    @ApiModelProperty("课程发布id")
    private Long coursePubId;

    @ApiModelProperty("是否支持试看")
    private String isPreview;

}
