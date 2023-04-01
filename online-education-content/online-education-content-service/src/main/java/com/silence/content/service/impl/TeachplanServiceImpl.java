package com.silence.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.content.mapper.TeachplanMapper;
import com.silence.content.model.dto.TeachplanTreeDTO;
import com.silence.content.model.dto.UpsertTeachplanDTO;
import com.silence.content.model.po.Teachplan;
import com.silence.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author silence
 */
@Slf4j
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {

    @Resource
    private TeachplanMapper teachplanMapper;

    @Override
    public List<TeachplanTreeDTO> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Override
    public void upsertTeachplan(UpsertTeachplanDTO upsertTeachplanDTO) {
        Long id = upsertTeachplanDTO.getId();
        // 修改
        if (id != null) {
            Teachplan teachPlan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(upsertTeachplanDTO, teachPlan);
            teachplanMapper.updateById(teachPlan);
        // 添加
        } else {
            int count = getTeachplanCount(upsertTeachplanDTO.getCourseId(), upsertTeachplanDTO.getParentid());
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(upsertTeachplanDTO, teachplan);
            teachplan.setOrderby(count);
            teachplanMapper.insert(teachplan);
        }
    }

    private int getTeachplanCount(long courseId, long parentId) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId, courseId);
        queryWrapper.eq(Teachplan::getParentid, parentId);
        return teachplanMapper.selectCount(queryWrapper);
    }

}
