package com.silence.media.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author silence
 * @Description 上传普通文件请求参数
 * @Date 2023/4/11
 */
@Data
@ApiModel(value = "UploadFileParamsDTO", description = "上传普通文件请求参数")
public class UploadFileParamsDTO {

    @ApiModelProperty("文件名称")
    private String filename;

    @ApiModelProperty("文件类型（文档，音频，视频）")
    private String fileType;

    @ApiModelProperty("文件大小")
    private Long fileSize;

    @ApiModelProperty("标签")
    private String tags;

    @ApiModelProperty("上传人")
    private String username;

    @ApiModelProperty("备注")
    private String remark;

}
