package com.silence.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.silence.content.model.po.CourseTeacher;

import java.util.List;

/**
 * <p>
 * 课程-教师关系表 服务类
 * </p>
 *
 * @author silence
 * @since 2023-03-17
 */
public interface CourseTeacherService extends IService<CourseTeacher> {

    /**
     * @Author silence
     * @Description 查询教师列表
     * @Date 2023/4/2
     */
    List<CourseTeacher> queryCourseTeacherList(long courseId);

    /**
     * @Author silence
     * @Description 添加或修改教师信息
     * @Date 2023/4/2
     */
    CourseTeacher upsertCourseTeacher(Long companyId, CourseTeacher courseTeacher);

}
