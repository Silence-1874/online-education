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
import com.silence.content.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author silence
 * @Description 课程查询接口
 * @Date 2023/3/19
 */
@RestController
@Api(tags = "课程信息")
public class CourseBaseInfoController {

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询")
    @PostMapping(value = "/course/list")
    public PageResult<CourseBase> listCourseBase(PageParams pageParams,
                                       @RequestBody(required = false) QueryCourseParamsDTO queryCourseParams) {
        return courseBaseInfoService.listCourseBase(pageParams, queryCourseParams);
    }

    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    public CourseBaseInfoDTO saveCourseBase(@RequestBody @Validated({ValidationGroups.Insert.class}) AddCourseDTO addCourseDTO) {
        // 暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.saveCourseBase(companyId, addCourseDTO);
    }

    @ApiOperation("根据id查询课程基本信息")
    @ApiImplicitParam(name = "courseId", value = "课程Id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/course/{courseId}")
    public CourseBaseInfoDTO getCourseBaseById(@PathVariable Long courseId) {
        //取出当前用户身份
        SecurityUtil.OeUser user = SecurityUtil.getUser();
        System.out.println(user);
        return courseBaseInfoService.getCourseBaseById(courseId);
    }

    @ApiOperation("修改课程基本信息")
    @PutMapping("/course")
    public CourseBaseInfoDTO updateCourseBase(@RequestBody @Validated(ValidationGroups.Update.class) UpdateCourseDTO updateCourseDTO) {
        // 暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.updateCourseBase(companyId, updateCourseDTO);
    }

    @ApiOperation("删除课程")
    @ApiImplicitParam(name = "courseId", value = "课程Id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping("/course/{courseId}")
    public void removeCourseById(@PathVariable Long courseId) {
        courseBaseInfoService.removeCourseById(courseId);
    }

}
