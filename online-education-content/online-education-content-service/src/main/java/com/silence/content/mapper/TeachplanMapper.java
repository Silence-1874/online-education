package com.silence.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.silence.content.model.dto.TeachplanTreeDTO;
import com.silence.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author silence
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    /**
     * @Author silence
     * @Description 查询教学计划树
     * @Date 2023/3/28
     */
    List<TeachplanTreeDTO> selectTreeNodes(Long courseId);

}
