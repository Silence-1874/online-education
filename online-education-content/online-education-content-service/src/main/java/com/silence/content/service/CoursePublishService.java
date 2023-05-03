package com.silence.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.silence.content.model.dto.CoursePreviewDTO;
import com.silence.content.model.po.CoursePublish;

import java.io.File;

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

    /**
     * @Author silence
     * @Description 提交审核
     * @Date 2023/5/3
     */
    void commitAudit(Long companyId, Long courseId);

    /**
     * @Author silence
     * @Description 课程发布
     * @Date 2023/5/3
     */
    void publish(Long companyId, Long courseId);

    /**
     * @Author silence
     * @Description 课程页面静态化
     * @Date 2023/5/3
     */
    File generateCourseHtml(Long courseId);

    /**
     * @Author silence
     * @Description 上传静态化页面
     * @Date 2023/5/3
     */
    void uploadCourseHtml(Long courseId, File file);

}
