package com.silence.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author silence
 * @Description 分页参数
 * @Date 2023/3/18
 */
@Data
@AllArgsConstructor
public class PageParams {

    // 当前页码
    private Long pageNo = 1L;

    // 每页记录数
    private Long pageSize = 10L;

}
