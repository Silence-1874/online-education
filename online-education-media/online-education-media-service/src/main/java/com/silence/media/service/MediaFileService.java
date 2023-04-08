package com.silence.media.service;

import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.media.model.dto.QueryMediaParamsDto;
import com.silence.media.model.po.MediaFiles;

/**
 * @author silence
 * @version 1.0
 * @description 媒资文件管理业务类
 * @date 2023/4/8
 */
public interface MediaFileService {

    /**
     * @param pageParams          分页参数
     * @param queryMediaParamsDto 查询条件
     * @return com.silence.base.model.PageResult<com.silence.media.model.po.MediaFiles>
     * @description 媒资文件查询方法
     * @author silence
     * @date 2023/4/8
     */
    public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);


}
