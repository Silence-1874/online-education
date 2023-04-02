package com.silence.content.api;

import com.silence.content.model.dto.CourseCategoryTreeDTO;
import com.silence.content.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author silence
 * @Description 课程分类接口
 * @Date 2023/3/23
 */
@RestController
@Api(tags = "课程分类")
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    @ApiOperation("查询某分类下的所有结点（不包括自身），并调整好结构")
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDTO> listTreeNode() {
        final String ROOT = "1";
        return courseCategoryService.listTreeNode(ROOT);
    }

}

