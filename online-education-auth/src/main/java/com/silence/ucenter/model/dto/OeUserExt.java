package com.silence.ucenter.model.dto;

import com.silence.ucenter.model.po.OeUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author silence
 * @Description 用户扩展信息
 * @Date 2023/5/4
 */
@Data
public class OeUserExt extends OeUser {

    /**
     * 用户权限
     */
    List<String> permissions = new ArrayList<>();

}
