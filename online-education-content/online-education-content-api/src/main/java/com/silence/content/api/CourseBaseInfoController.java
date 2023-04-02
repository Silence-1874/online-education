package com.silence.content.api;

import com.silence.base.exception.ValidationGroups;
import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.content.model.dto.AddCourseDTO;
import com.silence.content.model.dto.CourseBaseInfoDTO;
import com.silence.content.model.dto.QueryCourseParamsDTO;
import com.silence.content.model.dto.UpdateCourseDTO;
import com.silence.content.model.po.CourseBase;
import com.silence.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author silence
 * @Description 课程查询接口
 * @Date 2023/3/19
 */
@Api(value = "课程信息编辑接口",tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询接口")
    @PostMapping(value = "/course/list")
    public PageResult<CourseBase> list(PageParams pageParams,
                                       @RequestBody(required = false) QueryCourseParamsDTO queryCourseParams) {
        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParams);
        return courseBasePageResult;
    }

    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    public CourseBaseInfoDTO createCourseBase(@RequestBody @Validated({ValidationGroups.Insert.class}) AddCourseDTO addCourseDTO) {
        // 暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.createCourseBase(companyId, addCourseDTO);
    }

    @ApiOperation("根据id查询课程基本信息")
    @GetMapping(value = "/course/{courseId}")
    public CourseBaseInfoDTO getCourseBaseById(@PathVariable Long courseId) {
        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }

    @ApiOperation("修改课程基本信息")
    @PutMapping("/course")
    public CourseBaseInfoDTO updateCourseBase(@RequestBody @Validated(ValidationGroups.Update.class) UpdateCourseDTO updateCourseDTO) {
        // 暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.updateCourseBaseInfo(companyId, updateCourseDTO);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/course/{courseId}")
    public void removeCourse(@PathVariable Long courseId) {
        courseBaseInfoService.removeCourse(courseId);
    }

}
