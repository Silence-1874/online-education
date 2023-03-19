package com.silence.content.api;

import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.model.dto.QueryCourseParamsDTO;
import com.silence.model.po.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author silence
 * @Description 课程查询接口
 * @Date 2023/3/19
 */
@Api(value = "课程信息编辑接口",tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {

    @ApiOperation("课程查询接口")
    @PostMapping(value = "/course/list")
    public PageResult<CourseBase> list(PageParams pageParams,
                                       @RequestBody(required = false) QueryCourseParamsDTO queryCourseParams) {
        return null;
    }

}
