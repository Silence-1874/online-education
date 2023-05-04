package com.silence.ucenter.service;

import com.silence.ucenter.model.dto.AuthParamsDTO;
import com.silence.ucenter.model.dto.OeUserExt;

/**
 * @Author silence
 * @Description 认证服务
 * @Date 2023/5/4
 */
public interface AuthService {

    /**
     * @Author silence
     * @Description 认证方法
     * @Date 2023/5/4
     */
    OeUserExt execute(AuthParamsDTO authParamsDto);

}
