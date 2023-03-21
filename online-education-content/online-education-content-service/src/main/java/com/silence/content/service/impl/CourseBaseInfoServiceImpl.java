package com.silence.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.content.mapper.CourseBaseMapper;
import com.silence.content.model.dto.QueryCourseParamsDTO;
import com.silence.content.model.po.CourseBase;
import com.silence.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程基本信息 服务实现类
 * </p>
 *
 * @author silence
 */
@Slf4j
@Service
public class CourseBaseInfoServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements CourseBaseInfoService {

    @Resource
    private CourseBaseMapper courseBaseMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDTO queryCourseParams) {
        // 构建查询条件对象
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        // 构建查询条件
        queryWrapper
                .like(StringUtils.isNotEmpty(queryCourseParams.getCourseName()), CourseBase::getName, queryCourseParams.getCourseName())
                .eq(StringUtils.isNotEmpty(queryCourseParams.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParams.getAuditStatus())
                .eq(StringUtils.isNotEmpty(queryCourseParams.getPublishStatus()), CourseBase::getStatus, queryCourseParams.getPublishStatus());
        // 分页对象
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<CourseBase> courseBasePage = courseBaseMapper.selectPage(page, queryWrapper);
        // 分页查询
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(courseBasePage, queryWrapper);
        List<CourseBase> items = pageResult.getRecords();
        long total = pageResult.getTotal();
        // 构建结果集
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(items, total, pageParams.getPageNo(), pageParams.getPageSize());
        return courseBasePageResult;
    }

}
