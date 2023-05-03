package com.silence.content.api;

import com.silence.content.model.dto.CoursePreviewDTO;
import com.silence.content.service.CourseBaseInfoService;
import com.silence.content.service.CoursePublishService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author silence
 * @Description 内容管理公开接口
 * @Date 2023/5/2
 */
@RestController
@Api(tags = "内容管理公开接口")
@RequestMapping("/open")
public class ContentOpenController {

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @Autowired
    private CoursePublishService coursePublishService;


    @GetMapping("/course/whole/{courseId}")
    public CoursePreviewDTO getPreviewInfo(@PathVariable("courseId") Long courseId) {
        return coursePublishService.getCoursePreviewInfo(courseId);
    }

}
