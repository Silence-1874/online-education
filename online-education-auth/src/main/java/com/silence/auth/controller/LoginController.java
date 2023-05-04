package com.silence.auth.controller;

import com.silence.ucenter.mapper.OeUserMapper;
import com.silence.ucenter.model.po.OeUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author silence
 * @Description 登陆测试接口
 * @Date 2023/5/4
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    OeUserMapper userMapper;

    @RequestMapping("/login-success")
    public String loginSuccess() {
        return "登录成功";
    }

    @RequestMapping("/user/{id}")
    public OeUser getuser(@PathVariable("id") String id) {
        OeUser oeUser = userMapper.selectById(id);
        return oeUser;
    }

    @RequestMapping("/r/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1() {
        return "访问r1资源";
    }

    @RequestMapping("/r/r2")
    @PreAuthorize("hasAuthority('p2')")
    public String r2() {
        return "访问r2资源";
    }

}