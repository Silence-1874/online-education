package com.silence.content.model.dto;

import com.silence.content.model.po.CourseCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author silence
 * @Description 分类树节点
 * @Date 2023/3/23
 */
@Data
@ApiModel(value = "CourseCategoryTreeDTO", description = "课程分类树节点")
public class CourseCategoryTreeDTO extends CourseCategory implements Serializable {

    /**
     * 子节点
     */
    @ApiModelProperty("子结点")
    private List<CourseCategoryTreeDTO> childrenTreeNodes;

}
