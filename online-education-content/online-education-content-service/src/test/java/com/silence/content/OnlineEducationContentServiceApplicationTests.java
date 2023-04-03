package com.silence.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.content.mapper.CourseBaseMapper;
import com.silence.content.model.dto.CourseCategoryTreeDTO;
import com.silence.content.model.dto.QueryCourseParamsDTO;
import com.silence.content.model.po.CourseBase;
import com.silence.content.service.CourseBaseInfoService;
import com.silence.content.service.CourseCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static com.silence.base.enums.CourseAuditStatusEnum.PASSED;
import static com.silence.base.enums.CoursePublicStatusEnum.NOT_PUBLISH;

@SpringBootTest
class OnlineEducationContentServiceApplicationTests {

    @Resource
    CourseBaseMapper courseBaseMapper;

    @Test
    void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(18L);
        Assertions.assertNotNull(courseBase);

        // 测试查询接口
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件
        QueryCourseParamsDTO queryCourseParamsDTO = new QueryCourseParamsDTO();
        queryCourseParamsDTO.setCourseName("java");
        queryCourseParamsDTO.setAuditStatus(PASSED.getCode());
        // 拼接查询条件
        queryWrapper
                .like(StringUtils.isNotEmpty(queryCourseParamsDTO.getCourseName()), CourseBase::getName, queryCourseParamsDTO.getCourseName())
                .eq(StringUtils.isNotEmpty(queryCourseParamsDTO.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDTO.getAuditStatus());
        // 分页参数
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1L);
        pageParams.setPageSize(10L);
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<CourseBase> courseBasePage = courseBaseMapper.selectPage(page, queryWrapper);
        // 分页查询
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(courseBasePage, queryWrapper);
        List<CourseBase> items = pageResult.getRecords();
        long total = pageResult.getTotal();
        // 准备返回数据
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(items, total, pageParams.getPageNo(), pageParams.getPageSize());
        System.out.println(courseBasePageResult);
    }

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @Test
    void testCourseBaseService() {
        //查询条件
        QueryCourseParamsDTO queryCourseParamsDto = new QueryCourseParamsDTO();
        queryCourseParamsDto.setCourseName("java");
        queryCourseParamsDto.setAuditStatus(PASSED.getCode());
        queryCourseParamsDto.setPublishStatus(NOT_PUBLISH.getCode());

        //分页参数
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1L);
        pageParams.setPageSize(3L);

        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.listCourseBase(pageParams, queryCourseParamsDto);
        System.out.println(courseBasePageResult);
    }

    @Autowired
    private CourseCategoryService courseCategoryService;

    @Test
    void testCategoryTreeNode() {
        List<CourseCategoryTreeDTO> list = courseCategoryService.listTreeNode("1");
        System.out.println(list);
    }

}
