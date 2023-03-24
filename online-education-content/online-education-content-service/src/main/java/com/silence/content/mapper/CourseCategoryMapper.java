package com.silence.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.silence.content.model.dto.CourseCategoryTreeDTO;
import com.silence.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author silence
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

    /**
     * @Author silence
     * @Description 查询某分类及其下的所有结点
     * @Date 2023/3/23
     */
    List<CourseCategoryTreeDTO> selectTreeNodes(String id);

}
