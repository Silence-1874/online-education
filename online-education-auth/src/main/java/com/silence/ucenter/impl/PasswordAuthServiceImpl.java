package com.silence.ucenter.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.silence.ucenter.mapper.OeUserMapper;
import com.silence.ucenter.model.dto.AuthParamsDTO;
import com.silence.ucenter.model.dto.OeUserExt;
import com.silence.ucenter.model.po.OeUser;
import com.silence.ucenter.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author silence
 * @Description 账号密码认证
 * @Date 2023/5/4
 */
@Service("password_authservice")
public class PasswordAuthServiceImpl implements AuthService {
    @Autowired
    OeUserMapper xcUserMapper;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public OeUserExt execute(AuthParamsDTO authParamsDto) {
        // 账号
        String username = authParamsDto.getUsername();
        OeUser user = xcUserMapper.selectOne(new LambdaQueryWrapper<OeUser>().eq(OeUser::getUsername, username));
        if (user == null) {
            // 返回空表示用户不存在
            throw new RuntimeException("账号不存在");
        }
        OeUserExt oeUserExt = new OeUserExt();
        BeanUtils.copyProperties(user, oeUserExt);
        // 校验密码
        // 取出数据库存储的正确密码
        String passwordDb = user.getPassword();
        String passwordForm = authParamsDto.getPassword();
        boolean matches = passwordEncoder.matches(passwordForm, passwordDb);
        if (!matches) {
            throw new RuntimeException("账号或密码错误");
        }
        return oeUserExt;
    }
}
