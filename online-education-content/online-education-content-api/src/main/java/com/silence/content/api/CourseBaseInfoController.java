package com.silence.content.api;

import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.model.dto.QueryCourseParamsDTO;
import com.silence.model.po.CourseBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author silence
 * @Description 课程查询接口
 * @Date 2023/3/19
 */
@RestController
public class CourseBaseInfoController {

    @PostMapping(value = "/course/list")
    public PageResult<CourseBase> list(PageParams pageParams,
                                       @RequestBody(required = false) QueryCourseParamsDTO queryCourseParams) {
        return null;
    }

}
