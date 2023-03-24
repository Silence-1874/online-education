package com.silence.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silence.content.mapper.CourseCategoryMapper;
import com.silence.content.model.dto.CourseCategoryTreeDTO;
import com.silence.content.model.po.CourseCategory;
import com.silence.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author silence
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {

    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDTO> queryTreeNodes(String id) {
        // 获得分类下的所有结点
        List<CourseCategoryTreeDTO> categoryTreeDTOs = courseCategoryMapper.selectTreeNodes(id);
        // 结点id -> 结点
        Map<String, CourseCategoryTreeDTO> id2ParentNode =
                categoryTreeDTOs.stream().collect(Collectors.toMap(CourseCategory::getId, x -> x, (x1, x2) -> x2));
        // 最终返回的列表
        List<CourseCategoryTreeDTO> retList = new ArrayList<>();
        categoryTreeDTOs.stream()
                // 排除根结点
                .filter(node -> !id.equals(node.getId()))
                .forEach(node -> {
                    // 加入直接子结点
                    if (node.getParentid().equals(id)) {
                        retList.add(node);
                    }
                    // 找到当前结点的父结点
                    CourseCategoryTreeDTO parent = id2ParentNode.get(node.getParentid());
                    if (parent != null) {
                        // 创建子结点列表
                        if (parent.getChildrenTreeNodes() == null) {
                            parent.setChildrenTreeNodes(new ArrayList<>());
                        }
                        // 添加子结点
                        parent.getChildrenTreeNodes().add(node);
                    }
                });
        return retList;
    }

}
