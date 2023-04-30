package com.silence.media.api;

import com.silence.base.enums.MediaTypeEnum;
import com.silence.base.model.PageParams;
import com.silence.base.model.PageResult;
import com.silence.base.model.RestResponse;
import com.silence.media.model.dto.QueryMediaParamsDTO;
import com.silence.media.model.dto.UploadFileParamsDTO;
import com.silence.media.model.po.MediaFiles;
import com.silence.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author silence
 * @version 1.0
 * @description 媒资文件管理接口
 * @date 2023/4/8
 */
@Api(value = "媒资文件管理接口", tags = "媒资文件管理接口")
@RestController
public class MediaFilesController {


    @Autowired
    MediaFileService mediaFileService;


    @ApiOperation("媒资列表查询接口")
    @PostMapping("/files")
    public PageResult<MediaFiles> list(PageParams pageParams, @RequestBody QueryMediaParamsDTO queryMediaParamsDto) {
        Long companyId = 1232141425L;
        return mediaFileService.queryMediaFiles(companyId, pageParams, queryMediaParamsDto);

    }

    @ApiOperation("上传文件")
    @PostMapping(value = "/upload/coursefile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MediaFiles upload(@RequestPart("filedata") MultipartFile filedata) throws IOException {
        Long companyId = 1232141425L;
        // 通过创建的临时文件获得文件路径
        File tempFile = File.createTempFile("minio", ".temp");
        filedata.transferTo(tempFile);
        String localFilePath = tempFile.getAbsolutePath();
        // 准备上传文件信息
        UploadFileParamsDTO uploadFileParamsDTO = new UploadFileParamsDTO();
        uploadFileParamsDTO.setFilename(filedata.getOriginalFilename());
        uploadFileParamsDTO.setFileSize(filedata.getSize());
        uploadFileParamsDTO.setFileType(MediaTypeEnum.IMAGE.getCode());
        // 上传文件
        MediaFiles mediaFile = mediaFileService.uploadFile(companyId, uploadFileParamsDTO, localFilePath);
        return mediaFile;
    }

    @ApiOperation(value = "文件上传前的检查")
    @PostMapping("/upload/checkfile")
    public RestResponse<Boolean> checkFile(@RequestParam("fileMd5") String fileMd5) {
        return mediaFileService.checkFile(fileMd5);
    }

    @ApiOperation(value = "分块上传前的检查")
    @PostMapping("/upload/checkchunk")
    public RestResponse<Boolean> checkchunk(@RequestParam("fileMd5") String fileMd5,
                                            @RequestParam("chunk") int chunk) {
        return mediaFileService.checkChunk(fileMd5,chunk);
    }


    @ApiOperation(value = "上传分块文件")
    @PostMapping("/upload/uploadchunk")
    public RestResponse uploadchunk(@RequestParam("file") MultipartFile file,
                                    @RequestParam("fileMd5") String fileMd5,
                                    @RequestParam("chunk") int chunk) throws IOException {
        // 创建临时文件
        File tempFile = File.createTempFile("minio", "temp");
        // 将上传的文件拷贝到临时文件
        file.transferTo(tempFile);
        // 获得文件路径
        String absolutePath = tempFile.getAbsolutePath();
        return mediaFileService.uploadChunk(fileMd5, chunk, absolutePath);
    }

    @ApiOperation(value = "合并文件")
    @PostMapping("/upload/mergechunks")
    public RestResponse mergechunks(@RequestParam("fileMd5") String fileMd5,
                                    @RequestParam("fileName") String fileName,
                                    @RequestParam("chunkTotal") int chunkTotal) {
        Long companyId = 1232141425L;
        UploadFileParamsDTO uploadFileParamsDTO = new UploadFileParamsDTO();
        uploadFileParamsDTO.setFilename(fileName);
        uploadFileParamsDTO.setTags("课程视频");
        uploadFileParamsDTO.setFileType(MediaTypeEnum.VIDEO.getCode());
        return mediaFileService.mergeChunks(companyId, fileMd5, chunkTotal, uploadFileParamsDTO);
    }

}
