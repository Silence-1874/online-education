package com.silence.content.api;

import com.silence.content.model.dto.TeachplanTreeDTO;
import com.silence.content.model.dto.UpsertTeachplanDTO;
import com.silence.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author silence
 * @Description 教学计划接口
 * @Date 2023/3/28
 */
@Api(value = "教学计划接口", tags = "教学计划接口")
@RestController
public class TeachplanController {

    @Autowired
    TeachplanService teachplanService;

    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
    @GetMapping("teachplan/{courseId}/tree-nodes")
    public List<TeachplanTreeDTO> getTreeNodes(@PathVariable Long courseId) {
        return teachplanService.findTeachplanTree(courseId);
    }

    @ApiOperation("添加或修改课程计划")
    @PostMapping("/teachplan")
    public void upsertTreeNode(@RequestBody UpsertTeachplanDTO teachplan) {
        teachplanService.upsertTeachplan(teachplan);
    }

    @ApiOperation("删除课程计划")
    @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
    @DeleteMapping("/teachplan/{courseId}")
    public void removeTreeNode(@PathVariable Long courseId) {
        teachplanService.deleteTeachplan(courseId);
    }

    @ApiOperation("移动计划顺序")
    @PostMapping("/teachplan/{direction}/{id}")
    public void moveOrder(@PathVariable String direction, @PathVariable Long id) {
        int dir = 0;
        if ("movedown".equals(direction)) {
            dir = -1;
        } else if ("moveup".equals(direction)) {
            dir = 1;
        }
        teachplanService.moveOrder(dir, id);
    }

}
