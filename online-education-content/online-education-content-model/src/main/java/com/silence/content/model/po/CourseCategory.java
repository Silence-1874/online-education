package com.silence.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 课程分类
 * </p>
 *
 * @author silence
 */
@Data
@ApiModel(value = "CourseCategory", description = "课程分类")
@TableName("course_category")
public class CourseCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String id;

    /**
     * 分类名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 分类标签默认和名称一样
     */
    @ApiModelProperty("标签")
    private String label;

    /**
     * 父结点id（第一级的父节点是0，自关联字段id）
     */
    @ApiModelProperty("父结点id（第一级的父节点是0，自关联字段id）")
    private String parentid;

    /**
     * 是否显示
     */
    @ApiModelProperty("是否显示")
    private Integer isShow;

    /**
     * 排序字段
     */
    @ApiModelProperty("排序字段")
    private Integer orderby;

    /**
     * 是否叶子
     */
    @ApiModelProperty("是否为叶子")
    private Integer isLeaf;


}
