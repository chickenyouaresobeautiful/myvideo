package com.daiweij.myvedio.modules.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "手机号不能为空")
    private String phoneNumber;

    @NotBlank(message = "验证码不能为空")
    private String verificationCode;
}
