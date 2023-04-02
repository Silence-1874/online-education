package com.silence.content.api;

import com.silence.content.model.dto.CourseCategoryTreeDTO;
import com.silence.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author silence
 * @Description 课程分类接口
 * @Date 2023/3/23
 */
@Slf4j
@RestController
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDTO> listTreeNode() {
        return courseCategoryService.listTreeNode("1");
    }

}

