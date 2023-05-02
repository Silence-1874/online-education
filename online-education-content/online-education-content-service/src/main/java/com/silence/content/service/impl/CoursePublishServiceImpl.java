package com.silence.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.content.mapper.CoursePublishMapper;
import com.silence.content.model.dto.CourseBaseInfoDTO;
import com.silence.content.model.dto.CoursePreviewDTO;
import com.silence.content.model.dto.TeachplanTreeDTO;
import com.silence.content.model.po.CoursePublish;
import com.silence.content.service.CourseBaseInfoService;
import com.silence.content.service.CoursePublishService;
import com.silence.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程发布 服务实现类
 * </p>
 *
 * @author silence
 */
@Slf4j
@Service
public class CoursePublishServiceImpl extends ServiceImpl<CoursePublishMapper, CoursePublish> implements CoursePublishService {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @Autowired
    TeachplanService teachplanService;

    @Override
    public CoursePreviewDTO getCoursePreviewInfo(Long courseId) {
        CourseBaseInfoDTO courseBaseInfo = courseBaseInfoService.getCourseBaseById(courseId);
        List<TeachplanTreeDTO> techplanTree = teachplanService.listTreeNode(courseId);
        CoursePreviewDTO coursePreviewDTO = new CoursePreviewDTO();
        coursePreviewDTO.setCourseBase(courseBaseInfo);
        coursePreviewDTO.setTeachplans(techplanTree);
        return coursePreviewDTO;
    }
}
