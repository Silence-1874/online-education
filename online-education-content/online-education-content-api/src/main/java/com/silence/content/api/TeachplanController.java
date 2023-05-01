package com.silence.content.api;

import com.silence.content.model.dto.BindTeachplanMediaDTO;
import com.silence.content.model.dto.TeachplanTreeDTO;
import com.silence.content.model.dto.UpsertTeachplanDTO;
import com.silence.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author silence
 * @Description 教学计划接口
 * @Date 2023/3/28
 */
@RestController
@Api(tags = "教学计划")
public class TeachplanController {

    @Autowired
    TeachplanService teachplanService;

    @ApiOperation("查询教学计划树")
    @ApiImplicitParam(name = "courseId", value = "课程Id", dataType = "Long", paramType = "path", required = true)
    @GetMapping("teachplan/{courseId}/tree-nodes")
    public List<TeachplanTreeDTO> listTreeNode(@PathVariable Long courseId) {
        return teachplanService.listTreeNode(courseId);
    }

    @ApiOperation("添加或修改教学计划")
    @PostMapping("/teachplan")
    public void upsertTreeNode(@RequestBody UpsertTeachplanDTO teachplan) {
        teachplanService.upsertTeachplan(teachplan);
    }

    @ApiOperation("删除教学计划")
    @ApiImplicitParam(name = "courseId", value = "课程Id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping("/teachplan/{courseId}")
    public void removeTreeNode(@PathVariable Long courseId) {
        teachplanService.removeTreeNode(courseId);
    }

    @ApiOperation("移动计划顺序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "direction", value = "移动方向，-1为向下移动，1为向上移动", dataType = "String", paramType = "path", required = true),
            @ApiImplicitParam(name = "id", value = "教学计划id", dataType = "Long", paramType = "path", required = true)
    })
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

    @ApiOperation(value = "绑定课程计划与媒资信息")
    @PostMapping("/teachplan/association/media")
    public void associateMedia(@RequestBody BindTeachplanMediaDTO bindTeachplanMediaDTO) {
        teachplanService.associateMedia(bindTeachplanMediaDTO);
    }

}
