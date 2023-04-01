package com.silence.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.silence.content.model.dto.TeachplanTreeDTO;
import com.silence.content.model.dto.UpsertTeachplanDTO;
import com.silence.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 服务类
 * </p>
 *
 * @author silence
 * @since 2023-03-17
 */
public interface TeachplanService extends IService<Teachplan> {

    /**
     * @Author silence
     * @Description 查询教学计划树
     * @Date 2023/3/30
     */
    List<TeachplanTreeDTO> findTeachplanTree(long courseId);

    /**
     * @Author silence
     * @Description 添加或修改课程计划
     * @Date 2023/4/1
     */
    void upsertTeachplan(UpsertTeachplanDTO upsertTeachplanDTO);

    /**
     * @Author silence
     * @Description 删除课程计划
     * @Date 2023/4/1
     */
    void deleteTeachplan(long courseId);

    /**
     * @Author silence
     * @Description 移动计划顺序，direction为-1时下移，为1时上移
     * @Date 2023/4/1
     */
    void moveOrder(int direction, long id);

}
