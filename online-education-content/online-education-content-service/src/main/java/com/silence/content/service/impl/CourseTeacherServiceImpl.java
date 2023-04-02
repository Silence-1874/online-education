package com.silence.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.base.exception.MyException;
import com.silence.content.mapper.CourseBaseMapper;
import com.silence.content.mapper.CourseTeacherMapper;
import com.silence.content.model.po.CourseBase;
import com.silence.content.model.po.CourseTeacher;
import com.silence.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private CourseBaseMapper courseBaseMapper;

    @Override
    public List<CourseTeacher> queryCourseTeacherList(long courseId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new  LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseId);
        return courseTeacherMapper.selectList(queryWrapper);
    }

    @Transactional
    @Override
    public CourseTeacher upsertCourseTeacher(Long companyId, CourseTeacher courseTeacher) {
        // 查找课程对应的机构
        CourseBase courseBase = courseBaseMapper.selectById(courseTeacher.getCourseId());
        if (!companyId.equals(courseBase.getCompanyId())) {
            MyException.cast("只能添加或修改本机构的教师");
        }
        Long id = courseTeacher.getId();
        // 修改
        if (id != null) {
            courseTeacherMapper.updateById(courseTeacher);
        // 添加
        } else {
            courseTeacherMapper.insert(courseTeacher);
        }
        return courseTeacher;
    }

    @Transactional
    @Override
    public void removeCourseTeacher(Long companyId, Long id) {
        // 查找课程对应的机构
        CourseTeacher courseTeacher = courseTeacherMapper.selectById(id);
        CourseBase courseBase = courseBaseMapper.selectById(courseTeacher.getCourseId());
        if (!companyId.equals(courseBase.getCompanyId())) {
            MyException.cast("只能删除本机构的教师");
        }
        courseTeacherMapper.deleteById(id);
    }

}
