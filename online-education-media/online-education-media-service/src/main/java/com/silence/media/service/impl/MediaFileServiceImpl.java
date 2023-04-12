package com.silence.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.silence.base.enums.CourseAuditStatusEnum;
import com.silence.base.enums.MediaStatusEnum;
import com.silence.base.exception.MyException;
import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.media.mapper.MediaFilesMapper;
import com.silence.media.model.dto.QueryMediaParamsDTO;
import com.silence.media.model.dto.UploadFileParamsDTO;
import com.silence.media.model.po.MediaFiles;
import com.silence.media.service.MediaFileService;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author silence
 * @version 1.0
 * @description TODO
 * @date 2023/4/8
 */
@Slf4j
@Service
public class MediaFileServiceImpl implements MediaFileService {

    @Autowired
    MediaFilesMapper mediaFilesMapper;

    @Override
    public PageResult<MediaFiles> queryMediaFiles(Long companyId, PageParams pageParams, QueryMediaParamsDTO queryMediaParamsDto) {

        // 构建查询条件对象
        LambdaQueryWrapper<MediaFiles> queryWrapper = new LambdaQueryWrapper<>();

        // 分页对象
        Page<MediaFiles> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<MediaFiles> pageResult = mediaFilesMapper.selectPage(page, queryWrapper);
        // 获取数据列表
        List<MediaFiles> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        PageResult<MediaFiles> mediaListResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return mediaListResult;

    }

    @Autowired
    MinioClient minioClient;

    // 普通文件桶
    @Value("${minio.bucket.files}")
    private String bucketFiles;

    /**
     * @Author silence
     * @Description 获取文件默认存储目录路径(年 / 月 / 日)
     * @Date 2023/4/11
     */
    private String getDefaultFolderPath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date()).replace("-", "/") + "/";
    }

    /**
     * @Author silence
     * @Description 获取文件的md5值
     * @Date 2023/4/11
     */
    private String getFileMd5(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            String fileMd5 = DigestUtils.md5Hex(fileInputStream);
            return fileMd5;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Author silence
     * @Description 根据扩展名获得MimeType
     * @Date 2023/4/11
     */
    private String getMimeType(String extension) {
        if (extension == null) {
            extension = "";
        }
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(extension);
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if (extensionMatch != null) {
            mimeType = extensionMatch.getMimeType();
        }
        return mimeType;
    }

    /**
     * @Author silence
     * @Description 将媒资文件上传到minio
     * @Date 2023/4/11
     */
    public boolean saveMediaFiles2Minio(String localFilePath, String mimeType, String bucket, String objectName) {
        try {
            UploadObjectArgs myBucket = UploadObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .filename(localFilePath)
                    .contentType(mimeType)
                    .build();
            minioClient.uploadObject(myBucket);
            log.debug("上传文件到minio成功,bucket:{},objectName:{}", bucket, objectName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件到minio出错,bucket:{},objectName:{},错误原因:{}", bucket, objectName, e.getMessage(), e);
            MyException.cast("上传文件到文件系统失败");
        }
        return false;
    }

    @Transactional
    @Override
    public MediaFiles saveMediaFiles2Db(Long companyId, String fileMd5, UploadFileParamsDTO uploadFileParamsDTO,
                                        String bucket, String objectName) {
        MediaFiles mediaFiles = mediaFilesMapper.selectById(fileMd5);
        if (mediaFiles != null) {
            return mediaFiles;
        }
        mediaFiles = new MediaFiles();
        BeanUtils.copyProperties(uploadFileParamsDTO, mediaFiles);
        mediaFiles.setId(fileMd5);
        mediaFiles.setFileId(fileMd5);
        mediaFiles.setCompanyId(companyId);
        mediaFiles.setUrl("/" + bucket + "/" + objectName);
        mediaFiles.setBucket(bucket);
        mediaFiles.setFilePath(objectName);
        mediaFiles.setCreateDate(LocalDateTime.now());
        mediaFiles.setAuditStatus(CourseAuditStatusEnum.COMMITTED.getCode());
        mediaFiles.setStatus(MediaStatusEnum.NOT_PROCESS.getCode());
        int insert = mediaFilesMapper.insert(mediaFiles);
        if (insert < 0) {
            log.error("保存文件信息到数据库失败,{}", mediaFiles);
            MyException.cast("保存文件信息失败");
        }
        log.debug("保存文件信息到数据库成功,{}", mediaFiles);
        return mediaFiles;
    }

    @Autowired
    MediaFileService currentProxy;

    @Override
    public MediaFiles uploadFile(Long companyId, UploadFileParamsDTO uploadFileParamsDTO, String localFilePath) {
        File file = new File(localFilePath);
        if (!file.exists()) {
            MyException.cast("文件不存在");
        }
        // 将媒资文件上传到minio
        String filename = uploadFileParamsDTO.getFilename();
        String extension = filename.substring(filename.lastIndexOf("."));
        String mimeType = getMimeType(extension);
        String fileMd5 = getFileMd5(file);
        String defaultFolderPath = getDefaultFolderPath();
        String  objectName = defaultFolderPath + fileMd5 + extension;
        boolean b = saveMediaFiles2Minio(localFilePath, mimeType, bucketFiles, objectName);
        if (!b) {
            MyException.cast("文件上传失败");
        }
        // 将文件信息存储到数据库
        MediaFiles mediaFiles = currentProxy.saveMediaFiles2Db(companyId, fileMd5, uploadFileParamsDTO, bucketFiles, objectName);
        //准备返回数据
        MediaFiles result = new MediaFiles();
        BeanUtils.copyProperties(mediaFiles, result);
        return result;
    }

}
