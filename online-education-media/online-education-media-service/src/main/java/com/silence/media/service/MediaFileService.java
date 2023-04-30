package com.silence.media.service;

import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.base.model.RestResponse;
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

    /**
     * @Author silence
     * @Description 将媒资信息写入到数据库
     * @Date 2023/4/11
     */
    MediaFiles saveMediaFiles2Db(Long companyId, String fileMd5, UploadFileParamsDTO uploadFileParamsDTO, String bucket, String objectName);

    /**
     * @Author silence
     * @Description 检查文件是否存在
     * @Date 2023/4/29
     */
    RestResponse<Boolean> checkFile(String fileMd5);
    
    /**
     * @Author silence
     * @Description 检查分块是否存在
     * @Date 2023/4/29
     */
    RestResponse<Boolean> checkChunk(String fileMd5, int chunkIndex);

    /**
     * @Author silence
     * @Description 上传分块
     * @Date 2023/4/30
     */
    RestResponse uploadChunk(String fileMd5, int chunk, String localChunkFilePath);

    /**
     * @Author silence
     * @Description 合并分块
     * @Date 2023/4/30
     */
    RestResponse mergeChunks(Long companyId, String fileMd5, int chunkTotal, UploadFileParamsDTO uploadFileParamsDTO);

}
