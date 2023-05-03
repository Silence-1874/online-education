package com.silence.content.service.jobhandler;

import com.silence.messagesdk.model.po.MqMessage;
import com.silence.messagesdk.service.MessageProcessAbstract;
import com.silence.messagesdk.service.MqMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author silence
 * @Description 课程发布任务处理
 * @Date 2023/5/3
 */
@Slf4j
@Component
public class CoursePublishTask extends MessageProcessAbstract {

    @Override
    public boolean execute(MqMessage mqMessage) {
        // 获取消息相关的业务信息
        String businessKey1 = mqMessage.getBusinessKey1();
        long courseId = Integer.parseInt(businessKey1);
        // 课程静态化
        generateCourseHtml(mqMessage, courseId);
        // 课程索引
        saveCourseIndex(mqMessage, courseId);
        // 课程缓存
        saveCourseCache(mqMessage, courseId);
        return true;
    }

    /**
     * @Author silence
     * @Description 生成课程静态化页面并上传至文件系统
     * @Date 2023/5/3
     */
    private void generateCourseHtml(MqMessage mqMessage, long courseId) {
        log.debug("开始进行课程静态化，课程id:{}", courseId);
        Long id = mqMessage.getId();
        MqMessageService mqMessageService = this.getMqMessageService();
        // 消息幂等性处理
        int stageOne = mqMessageService.getStageOne(id);
        if (stageOne > 0) {
            log.debug("课程静态化已处理直接返回，课程id:{}", courseId);
            return;
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 保存第一阶段状态
        mqMessageService.completedStageOne(id);
    }

    /**
     * @Author silence
     * @Description 缓存课程
     * @Date 2023/5/3
     */
    public void saveCourseCache(MqMessage mqMessage, long courseId) {
        // TODO 缓存
        log.debug("课程信息缓存，课程id:{}", courseId);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author silence
     * @Description 保存课程索引信息
     * @Date 2023/5/3
     */
    public void saveCourseIndex(MqMessage mqMessage, long courseId) {
        // TODO 索引
        log.debug("保存课程索引信息，课程id:{}", courseId);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
