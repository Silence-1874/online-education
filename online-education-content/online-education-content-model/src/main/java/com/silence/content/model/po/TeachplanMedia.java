package com.silence.content.model.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author silence
 */
@Data
@ApiModel(value = "TeachplanMedia", description = "媒资信息")
@TableName("teachplan_media")
public class TeachplanMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 媒资文件id
     */
    @ApiModelProperty("媒资文件id")
    private String mediaId;

    /**
     * 课程计划标识
     */
    @ApiModelProperty("课程计划标识")
    private Long teachplanId;

    /**
     * 课程标识
     */
    @ApiModelProperty("课程标识")
    private Long courseId;

    /**
     * 媒资文件原始名称
     */
    @ApiModelProperty("媒资文件原始名称")
    @TableField("media_fileName")
    private String mediaFilename;

    @ApiModelProperty("创建日期")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createPeople;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String changePeople;


}
