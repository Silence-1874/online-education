package com.silence.content.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author silence
 * @Description 课程预览数据模型
 * @Date 2023/5/2
 */
@Data
public class CoursePreviewDTO {

    // 课程基本信息和营销信息
    CourseBaseInfoDTO courseBase;

    // 课程计划信息
    List<TeachplanTreeDTO> teachplans;

}
