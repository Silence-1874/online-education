package com.silence.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.content.model.dto.QueryCourseParamsDTO;
import com.silence.content.model.po.CourseBase;

/**
 * <p>
 * 课程基本信息 服务类
 * </p>
 *
 * @author silence
 * @since 2023-03-17
 */
public interface CourseBaseInfoService extends IService<CourseBase> {

    /**
     * @Author silence
     * @Description 课程查询
     * @Date 2023/3/20
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDTO queryCourseParams);

}
