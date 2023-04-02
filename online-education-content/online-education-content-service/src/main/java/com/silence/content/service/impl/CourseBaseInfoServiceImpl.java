package com.silence.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.base.exception.MyException;
import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.content.mapper.*;
import com.silence.content.model.dto.AddCourseDTO;
import com.silence.content.model.dto.CourseBaseInfoDTO;
import com.silence.content.model.dto.QueryCourseParamsDTO;
import com.silence.content.model.dto.UpdateCourseDTO;
import com.silence.content.model.po.*;
import com.silence.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private CourseMarketMapper courseMarketMapper;

    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    @Resource
    private CourseTeacherMapper courseTeacherMapper;

    @Resource
    private TeachplanMapper teachplanMapper;

    @Resource
    private TeachplanMediaMapper teachplanMediaMapper;

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

    @Transactional
    @Override
    public CourseBaseInfoDTO createCourseBase(Long companyId, AddCourseDTO addCourseDTO) {
        // 参数合法性校验
        if (StringUtils.isBlank(addCourseDTO.getName())) {
            throw new MyException("课程名称为空");
        }
        if (StringUtils.isBlank(addCourseDTO.getMt())) {
            throw new MyException("课程分类为空");
        }
        if (StringUtils.isBlank(addCourseDTO.getSt())) {
            throw new MyException("课程分类为空");
        }
        if (StringUtils.isBlank(addCourseDTO.getGrade())) {
            throw new MyException("课程等级为空");
        }
        if (StringUtils.isBlank(addCourseDTO.getUsers())) {
            throw new MyException("适用人群为空");
        }
        if (StringUtils.isBlank(addCourseDTO.getCharge())) {
            throw new MyException("收费规则为空");
        }

        // 新增课程基本信息
        CourseBase courseBaseNew = new CourseBase();
        BeanUtils.copyProperties(addCourseDTO, courseBaseNew);
        courseBaseNew.setCompanyId(companyId);
        courseBaseNew.setAuditStatus("202002");
        courseBaseNew.setStatus("203001");
        courseBaseNew.setCreateDate(LocalDateTime.now());
        if (courseBaseMapper.insert(courseBaseNew) <= 0) {
            throw new MyException("新增课程基本信息失败");
        };

        // 新增课程营销信息
        CourseMarket courseMarketNew = new CourseMarket();
        BeanUtils.copyProperties(addCourseDTO, courseMarketNew);
        courseMarketNew.setId(courseBaseNew.getId());
        if (upsertCourseMarket(courseMarketNew) <= 0) {
            throw new MyException("新增课程营销信息失败");
        }

        // 返回信息
        CourseBaseInfoDTO courseBaseInfoDTO = new CourseBaseInfoDTO();
        BeanUtils.copyProperties(courseBaseNew, courseBaseInfoDTO);
        BeanUtils.copyProperties(courseMarketNew, courseBaseInfoDTO);
        courseBaseInfoDTO.setMtName(courseCategoryMapper.selectById(courseBaseInfoDTO.getMt()).getName());
        courseBaseInfoDTO.setStName(courseCategoryMapper.selectById(courseBaseInfoDTO.getSt()).getName());
        return courseBaseInfoDTO;
    }

    private int upsertCourseMarket(CourseMarket courseMarketNew) {
        // 课程收费时价格参数校验
        if ("201001".equals(courseMarketNew.getCharge())) {
            Float price = courseMarketNew.getPrice();
            if (price == null || price <= 0) {
                throw new MyException("价格不合法");
            }
        }
        if ("201000".equals(courseMarketNew.getCharge())) {
            courseMarketNew.setPrice(0f);
            courseMarketNew.setOriginalPrice(0f);
        }
        // 添加或修改营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseMarketNew.getId());
        if (courseMarket == null) {
            return courseMarketMapper.insert(courseMarketNew);
        } else {
            return courseMarketMapper.updateById(courseMarketNew);
        }
    }

    @Override
    public CourseBaseInfoDTO getCourseBaseInfo(Long courseId) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            MyException.cast("课程不存在");
        }
        CourseBaseInfoDTO courseBaseInfoDTO = new CourseBaseInfoDTO();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDTO);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        if (courseMarket != null) {
            BeanUtils.copyProperties(courseMarket, courseBaseInfoDTO);
        }
        courseBaseInfoDTO.setMtName(courseCategoryMapper.selectById(courseBaseInfoDTO.getMt()).getName());
        courseBaseInfoDTO.setStName(courseCategoryMapper.selectById(courseBaseInfoDTO.getSt()).getName());
        return courseBaseInfoDTO;
    }

    @Transactional
    @Override
    public CourseBaseInfoDTO updateCourseBaseInfo(Long companyId, UpdateCourseDTO updateCourseDTO) {
        Long courseId = updateCourseDTO.getId();
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            MyException.cast("课程不存在");
        }
        if (!courseBase.getCompanyId().equals(companyId)) {
            MyException.cast("无法修改本机构以外的课程");
        }

        BeanUtils.copyProperties(updateCourseDTO, courseBase);
        courseBase.setChangeDate(LocalDateTime.now());
        if (courseBaseMapper.updateById(courseBase) <= 0) {
            MyException.cast("修改课程失败");
        }

        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        BeanUtils.copyProperties(updateCourseDTO, courseMarket);
        courseBase.setChangeDate(LocalDateTime.now());
        if (upsertCourseMarket(courseMarket) <= 0) {
            MyException.cast("修改课程失败");
        }
        return this.getCourseBaseInfo(courseId);
    }

    @Override
    public void removeCourse(Long courseId) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (!courseBase.getAuditStatus().equals("202002")) {
            MyException.cast("只能删除未提交审核的课程");
        }
        // 删除课程基本信息
        courseBaseMapper.deleteById(courseId);
        // 删除课程营销信息
        LambdaQueryWrapper<CourseMarket> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(CourseMarket::getId, courseId);
        courseMarketMapper.delete(queryWrapper1);
        // 删除课程教学计划
        LambdaQueryWrapper<Teachplan> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Teachplan::getCourseId, courseId);
        teachplanMapper.delete(queryWrapper2);
        // 删除课程教学计划关联的媒资信息
        LambdaQueryWrapper<TeachplanMedia> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(TeachplanMedia::getCourseId, courseId);
        teachplanMediaMapper.delete(queryWrapper3);
        // 删除课程师资信息
        LambdaQueryWrapper<CourseTeacher>  queryWrapper4 = new LambdaQueryWrapper<>();
        queryWrapper4.eq(CourseTeacher::getCourseId, courseId);
        courseTeacherMapper.delete(queryWrapper4);
    }

}
