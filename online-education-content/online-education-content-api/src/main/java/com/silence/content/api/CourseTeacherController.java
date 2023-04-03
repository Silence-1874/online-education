package com.silence.content.api;

import com.silence.content.model.po.CourseTeacher;
import com.silence.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@Api(tags = "课程师资")
public class CourseTeacherController {

    @Autowired
    private CourseTeacherService courseTeacherService;

    @ApiOperation("查询教师")
    @ApiImplicitParam(name = "courseId", value = "课程Id", dataType = "Long", paramType = "path", required = true)
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> listTeacher(@PathVariable Long courseId) {
        return courseTeacherService.listTeacher(courseId);
    }

    @ApiOperation("添加教师")
    @PostMapping("/courseTeacher")
    public CourseTeacher saveTeacher(@RequestBody CourseTeacher courseTeacher) {
        // 暂时硬编码
        Long companyId = 1232141425L;
        return courseTeacherService.upsertTeacher(companyId, courseTeacher);
    }

    @ApiOperation("修改教师")
    @PutMapping("/courseTeacher")
    public CourseTeacher updateTeacher(@RequestBody CourseTeacher courseTeacher) {
        // 暂时硬编码
        Long companyId = 1232141425L;
        return courseTeacherService.upsertTeacher(companyId, courseTeacher);
    }

    @ApiOperation("删除教师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程Id", dataType = "Long", paramType = "path", required = true),
            @ApiImplicitParam(name = "id", value = "教师Id", dataType = "Long", paramType = "path", required = true)
    })
    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public void removeTeacher(@PathVariable Long courseId, @PathVariable Long id) {
        // 暂时硬编码
        Long companyId = 1232141425L;
        courseTeacherService.removeTeacher(companyId, id);
    }

}
