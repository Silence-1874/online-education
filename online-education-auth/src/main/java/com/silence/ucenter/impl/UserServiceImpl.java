package com.silence.ucenter.impl;

import com.alibaba.fastjson.JSON;
import com.silence.ucenter.mapper.OeUserMapper;
import com.silence.ucenter.model.dto.AuthParamsDTO;
import com.silence.ucenter.model.dto.OeUserExt;
import com.silence.ucenter.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author silence
 * @Description 自定义用户信息服务
 * @Date 2023/5/4
 */
@Slf4j
@Component
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    OeUserMapper oeUserMapper;

    @Autowired
    ApplicationContext applicationContext;

    /**
     * @Author silence
     * @Description 根据账号查询用户信息
     * @Date 2023/5/4
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthParamsDTO authParamsDto = null;
        try {
            // 将认证参数转为AuthParamsDTO类型
            authParamsDto = JSON.parseObject(username, AuthParamsDTO.class);
        } catch (Exception e) {
            log.info("认证请求不符合项目要求:{}", username);
            throw new RuntimeException("认证请求数据格式不对");
        }
        String authType = authParamsDto.getAuthType();
        // 根据认证类型从spring容器取出指定的bean
        String beanName = authType + "_authservice";
        AuthService authService = applicationContext.getBean(beanName, AuthService.class);
        // 调用统一execute方法完成认证
        OeUserExt oeUserExt = authService.execute(authParamsDto);
        // 封装oeUserExt用户信息为UserDetails
        return getUserPrincipal(oeUserExt);
    }

    /**
     * @Author silence
     * @Description 查询用户信息
     * @Date 2023/5/4
     */
    public UserDetails getUserPrincipal(OeUserExt oeUser){
        String password = oeUser.getPassword();
        //权限
        String[] authorities=  {"test"};
        oeUser.setPassword(null);
        //将用户信息转json
        String userJson = JSON.toJSONString(oeUser);
        return User.withUsername(userJson).password(password).authorities(authorities).build();
    }

}
