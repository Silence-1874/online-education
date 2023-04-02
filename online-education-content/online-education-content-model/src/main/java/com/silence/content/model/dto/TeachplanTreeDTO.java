package com.silence.content.model.dto;

import com.silence.content.model.po.Teachplan;
import com.silence.content.model.po.TeachplanMedia;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author silence
 * @Description 教学计划树节点
 * @Date 2023/3/28
 */
@Data
@ApiModel(value = "TeachplanTreeDTO", description = "教学计划")
public class TeachplanTreeDTO extends Teachplan {

    @ApiModelProperty("课程关联的媒资信息")
    TeachplanMedia teachplanMedia;

    @ApiModelProperty("子节点")
    List<TeachplanTreeDTO> childrenTreeNodes;

}
