package com.silence.content.model.dto;

import com.silence.content.model.po.CourseCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author silence
 * @Description 分类树节点
 * @Date 2023/3/23
 */
@Data
public class CourseCategoryTreeDTO extends CourseCategory implements Serializable {

    /**
     * 子节点
     */
    private List<CourseCategoryTreeDTO> childrenTreeNodes;

}
