package com.silence.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author silence
 * @Description 分页参数
 * @Date 2023/3/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParams {

    @ApiModelProperty("页码")
    private Long pageNo = 1L;

    @ApiModelProperty("每页记录数")
    private Long pageSize = 10L;

}
