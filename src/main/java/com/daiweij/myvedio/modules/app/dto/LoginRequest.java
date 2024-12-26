package com.daiweij.myvedio.modules.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 登录表单
 */
@Data
public class LoginRequest {
    @NotBlank(message = "手机号、用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "登录类型不能为空")
    private Integer loginType;
}
