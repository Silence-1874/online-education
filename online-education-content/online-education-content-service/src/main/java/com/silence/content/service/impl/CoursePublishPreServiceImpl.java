package com.silence.content.service.impl;

import com.silence.content.model.po.CoursePublishPre;
import com.silence.content.mapper.CoursePublishPreMapper;
import com.silence.content.service.CoursePublishPreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 课程发布 服务实现类
 * </p>
 *
 * @author silence
 */
@Slf4j
@Service
public class CoursePublishPreServiceImpl extends ServiceImpl<CoursePublishPreMapper, CoursePublishPre> implements CoursePublishPreService {

}
