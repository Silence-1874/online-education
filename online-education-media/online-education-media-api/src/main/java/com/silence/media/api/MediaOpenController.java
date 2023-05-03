package com.silence.media.api;

import com.silence.base.exception.MyException;
import com.silence.base.model.RestResponse;
import com.silence.media.model.po.MediaFiles;
import com.silence.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author silence
 * @Description 媒资管理公开接口
 * @Date 2023/5/2
 */
@RestController
@Api(tags = "媒资管理公开接口")
@RequestMapping("/open")
public class MediaOpenController {

    @Autowired
    MediaFileService mediaFileService;

    @ApiOperation("预览文件")
    @GetMapping("/preview/{mediaId}")
    public RestResponse<String> getPlayUrlByMediaId(@PathVariable String mediaId) {
        MediaFiles mediaFiles = mediaFileService.getById(mediaId);
        if (mediaFiles == null || StringUtils.isEmpty(mediaFiles.getUrl())) {
            MyException.cast("视频还没有转码处理");
        }
        return RestResponse.success(mediaFiles.getUrl());
    }

}
