package com.silence.media.service;

import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.media.model.dto.QueryMediaParamsDTO;
import com.silence.media.model.dto.UploadFileParamsDTO;
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
    PageResult<MediaFiles> queryMediaFiles(Long companyId, PageParams pageParams, QueryMediaParamsDTO queryMediaParamsDto);

    /**
     * @Author silence
     * @Description 上传文件
     * @Date 2023/4/11
     */
    MediaFiles uploadFile(Long companyId, UploadFileParamsDTO uploadFileParamsDTO, String localFilePath);


}
