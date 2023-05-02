package com.silence.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.silence.content.model.dto.CoursePreviewDTO;
import com.silence.content.model.po.CoursePublish;

/**
 * <p>
 * 课程发布 服务类
 * </p>
 *
 * @author silence
 * @since 2023-05-02
 */
public interface CoursePublishService extends IService<CoursePublish> {

    /**
     * @Author silence
     * @Description 获得课程预览信息
     * @Date 2023/5/2
     */
    CoursePreviewDTO getCoursePreviewInfo(Long courseId);

}
