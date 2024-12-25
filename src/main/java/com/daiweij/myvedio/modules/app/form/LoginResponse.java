package com.daiweij.myvedio.modules.app.form;

import com.daiweij.myvedio.modules.sys.entity.UsersEntity;
import lombok.Data;

/**
 * 需要返回的登录信息
 */
@Data
public class LoginResponse {
    private int statusCode;
    private String token;
    private String message;
    private UsersEntity user;
}
