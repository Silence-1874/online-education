package com.silence.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.content.model.dto.AddCourseDTO;
import com.silence.content.model.dto.CourseBaseInfoDTO;
import com.silence.content.model.dto.QueryCourseParamsDTO;
import com.silence.content.model.dto.UpdateCourseDTO;
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

    /**
     * @Author silence
     * @Description 添加课程基本信息
     * @Date 2023/3/24
     */
    CourseBaseInfoDTO createCourseBase(Long companyId, AddCourseDTO addCourseDTO);

    /**
     * @Author silence
     * @Description 根据id查询课程基本信息
     * @Date 2023/3/27
     */
    CourseBaseInfoDTO getCourseBaseInfo(Long courseId);

    /**
     * @Author silence
     * @Description 修改课程基本信息
     * @Date 2023/3/27
     */
    CourseBaseInfoDTO updateCourseBaseInfo(Long companyId, UpdateCourseDTO updateCourseDTO);

    /**
     * @Author silence
     * @Description 删除课程
     * @Date 2023/4/2
     */
    void removeCourse(Long courseId);

}
