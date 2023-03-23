package com.silence.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.content.mapper.CourseCategoryMapper;
import com.silence.content.model.po.CourseCategory;
import com.silence.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author silence
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {

}
