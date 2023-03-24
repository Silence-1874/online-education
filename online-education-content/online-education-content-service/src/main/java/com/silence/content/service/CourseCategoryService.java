package com.silence.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.silence.content.model.dto.CourseCategoryTreeDTO;
import com.silence.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author silence
 * @since 2023-03-23
 */
public interface CourseCategoryService extends IService<CourseCategory> {

    /**
     * @Author silence
     * @Description 查询某分类下的所有结点（不包括自身），并调整好结构
     * @Date 2023/3/23
     */
    List<CourseCategoryTreeDTO> queryTreeNodes(String id);

}
