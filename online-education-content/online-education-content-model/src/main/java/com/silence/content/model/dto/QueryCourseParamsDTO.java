package com.silence.content.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author silence
 * @Description 查询课程请求参数
 * @Date 2023/3/19
 */
@Data
public class QueryCourseParamsDTO {

    @ApiModelProperty("审核状态")
    private String auditStatus;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("发布状态")
    private String publishStatus;

}
