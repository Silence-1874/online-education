package com.silence.ucenter.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.silence.ucenter.mapper.OeUserMapper;
import com.silence.ucenter.model.po.OeUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * @Author silence
     * @Description 根据账号查询用户信息
     * @Date 2023/5/4
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OeUser user = oeUserMapper.selectOne(new LambdaQueryWrapper<OeUser>().eq(OeUser::getUsername, username));
        // 用户不存在
        if (user == null) {
            return null;
        }
        String password = user.getPassword();
        String[] authorities= {"test"};
        return User.withUsername(user.getUsername()).password(password).authorities(authorities).build();
    }

}
