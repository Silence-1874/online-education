package com.silence.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.content.mapper.CourseTeacherMapper;
import com.silence.content.model.po.CourseTeacher;
import com.silence.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程-教师关系表 服务实现类
 * </p>
 *
 * @author silence
 */
@Slf4j
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements CourseTeacherService {

    @Resource
    private CourseTeacherMapper courseTeacherMapper;

    @Override
    public List<CourseTeacher> queryCourseTeacherList(long courseId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new  LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseId);
        return courseTeacherMapper.selectList(queryWrapper);
    }

}
