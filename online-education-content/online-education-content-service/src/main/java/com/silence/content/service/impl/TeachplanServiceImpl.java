package com.silence.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.base.exception.MyException;
import com.silence.content.mapper.TeachplanMapper;
import com.silence.content.mapper.TeachplanMediaMapper;
import com.silence.content.model.dto.TeachplanTreeDTO;
import com.silence.content.model.dto.UpsertTeachplanDTO;
import com.silence.content.model.po.Teachplan;
import com.silence.content.model.po.TeachplanMedia;
import com.silence.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public List<TeachplanTreeDTO> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Transactional
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

    @Transactional
    @Override
    public void deleteTeachplan(long courseId) {
        Teachplan teachplan = teachplanMapper.selectById(courseId);
        Long id = teachplan.getId();
        // 删除章
        if (teachplan.getGrade() == 1) {
            // 查找章下的小节
            LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
            Integer cnt = teachplanMapper.selectCount(queryWrapper.eq(Teachplan::getParentid, id));
            // 若章下还有小节，不能直接删除章
            if (cnt != 0) {
                MyException.cast("该章下还有小节，无法操作");
            // 章下无小节，删除该章
            } else {
                teachplanMapper.deleteById(id);
            }
        // 删除小节及关联的媒资文件
        } else if (teachplan.getGrade() == 2) {
            LambdaQueryWrapper<TeachplanMedia> queryWrapper = new LambdaQueryWrapper<>();
            teachplanMediaMapper.delete(queryWrapper.eq(TeachplanMedia::getTeachplanId, id));
            teachplanMapper.deleteById(id);
        }

    }

    private int getTeachplanCount(long courseId, long parentId) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId, courseId);
        queryWrapper.eq(Teachplan::getParentid, parentId);
        return teachplanMapper.selectCount(queryWrapper);
    }

}
