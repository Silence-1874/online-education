package com.silence.model.dto;

import lombok.Data;

/**
 * @Author silence
 * @Description 查询课程请求参数
 * @Date 2023/3/19
 */
@Data
public class QueryCourseParamsDTO {

    // 审核状态
    private String auditStatus;

    // 课程名称
    private String courseName;

    // 发布状态
    private String publishStatus;

}
