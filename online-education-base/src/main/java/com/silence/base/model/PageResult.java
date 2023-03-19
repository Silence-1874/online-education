package com.silence.base.model;

import lombok.Data;

import java.util.List;

/**
 * @Author silence
 * @Description 分页结果基础参数
 * @Date 2023/3/19
 */
@Data
public class PageResult<T> {

    // 数据列表
    private List<T> items;

    // 总纪录数
    private Long counts;

    // 当前页码
    private Long page;

    // 每页纪录数
    private Long pageSize;

}
