package com.silence.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.base.enums.CourseAuditStatusEnum;
import com.silence.base.exception.MyException;
import com.silence.content.mapper.CourseBaseMapper;
import com.silence.content.mapper.CourseMarketMapper;
import com.silence.content.mapper.CoursePublishMapper;
import com.silence.content.mapper.CoursePublishPreMapper;
import com.silence.content.model.dto.CourseBaseInfoDTO;
import com.silence.content.model.dto.CoursePreviewDTO;
import com.silence.content.model.dto.TeachplanTreeDTO;
import com.silence.content.model.po.CourseBase;
import com.silence.content.model.po.CourseMarket;
import com.silence.content.model.po.CoursePublish;
import com.silence.content.model.po.CoursePublishPre;
import com.silence.content.service.CourseBaseInfoService;
import com.silence.content.service.CoursePublishService;
import com.silence.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Autowired
    CoursePublishPreMapper coursePublishPreMapper;

    @Override
    public CoursePreviewDTO getCoursePreviewInfo(Long courseId) {
        CourseBaseInfoDTO courseBaseInfo = courseBaseInfoService.getCourseBaseById(courseId);
        List<TeachplanTreeDTO> techplanTree = teachplanService.listTreeNode(courseId);
        CoursePreviewDTO coursePreviewDTO = new CoursePreviewDTO();
        coursePreviewDTO.setCourseBase(courseBaseInfo);
        coursePreviewDTO.setTeachplans(techplanTree);
        return coursePreviewDTO;
    }

    @Override
    public void commitAudit(Long companyId, Long courseId) {
        // 约束校验
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        String auditStatus = courseBase.getAuditStatus();
        if (CourseAuditStatusEnum.COMMITTED.getCode().equals(auditStatus)) {
            MyException.cast("当前为等待审核状态，审核完成可以再次提交。");
        }
        if (!courseBase.getCompanyId().equals(companyId)) {
            MyException.cast("不允许提交其它机构的课程。");
        }
        if (StringUtils.isEmpty(courseBase.getPic())) {
            MyException.cast("提交失败，请上传课程图片");
        }
        // 添加课程预发布记录
        CoursePublishPre coursePublishPre = new CoursePublishPre();
        CourseBaseInfoDTO courseBaseInfo = courseBaseInfoService.getCourseBaseById(courseId);
        BeanUtils.copyProperties(courseBaseInfo, coursePublishPre);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        // 将课程营销信息转为json放入课程预发布表
        String courseMarketJson = JSON.toJSONString(courseMarket);
        coursePublishPre.setMarket(courseMarketJson);
        // 查询课程计划信息
        List<TeachplanTreeDTO> teachplanTree = teachplanService.listTreeNode(courseId);
        if (teachplanTree.size() <= 0) {
            MyException.cast("提交失败，还没有添加课程计划");
        }
        // 将课程计划信息转为json放入课程预发布表
        String teachplanTreeString = JSON.toJSONString(teachplanTree);
        coursePublishPre.setTeachplan(teachplanTreeString);
        coursePublishPre.setStatus(CourseAuditStatusEnum.COMMITTED.getCode());
        coursePublishPre.setCompanyId(companyId);
        coursePublishPre.setCreateDate(LocalDateTime.now());
        CoursePublishPre coursePublishPreUpdate = coursePublishPreMapper.selectById(courseId);
        if (coursePublishPreUpdate == null) {
            coursePublishPreMapper.insert(coursePublishPre);
        } else {
            coursePublishPreMapper.updateById(coursePublishPre);
        }
        // 更新课程基本表的审核状态
        courseBase.setAuditStatus(CourseAuditStatusEnum.COMMITTED.getCode());
        courseBaseMapper.updateById(courseBase);
    }

}
