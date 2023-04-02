package com.silence.content.api;

import com.silence.content.model.po.CourseTeacher;
import com.silence.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author silence
 * @Description 课程师资管理接口
 * @Date 2023/4/2
 */
@RestController
@Api(value = "课程师资管理接口",tags = "课程师资管理接口")
public class CourseTeacherController {

    @Autowired
    private CourseTeacherService courseTeacherService;

    @ApiOperation("查询教师")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> list(@PathVariable Long courseId) {
        return courseTeacherService.queryCourseTeacherList(courseId);
    }

}
