package com.daiweij.myvedio.modules.app.controller;

import com.daiweij.myvedio.common.exception.CustomException;
import com.daiweij.myvedio.common.utils.LogUtil;
import com.daiweij.myvedio.common.utils.R;
import com.daiweij.myvedio.common.validator.ValidatorUtils;
import com.daiweij.myvedio.modules.app.dto.LoginRequest;
import com.daiweij.myvedio.modules.app.dto.LoginResponse;
import com.daiweij.myvedio.modules.app.dto.RegisterRequest;
import com.daiweij.myvedio.modules.app.service.AppLoginService;
import com.daiweij.myvedio.modules.sys.entity.UsersEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/app")
public class AppLoginController {
    @Autowired
    private AppLoginService appLoginService;

    @PostMapping("/login")
    public R<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            //校验loginForm数据是否合法
            ValidatorUtils.validateEntity(loginRequest);

            //用户登录
            LoginResponse response = appLoginService.login(loginRequest);
            return R.success(response);
        } catch (CustomException e) {
            LogUtil.error(this.getClass(), e.getMessage());
            return R.error(e.getCode(), e.getMsg());
        }
    }

    @PostMapping("/register")
    public R<Map<String, Object>> register(@RequestBody RegisterRequest registerRequest) {
        try {
            //表单校验
            ValidatorUtils.validateEntity(registerRequest);

            appLoginService.register(registerRequest);
            return R.success();
        } catch (CustomException e) {
            LogUtil.error(this.getClass(), e.getMessage());
            return R.error(e.getCode(), e.getMsg());
        }
    }

    @GetMapping("/currentUser")
    public R<UsersEntity> currentUserInfo() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (username == null) {
            return R.error(500, "用户未登录");
        }
        LogUtil.info(this.getClass(), "当前用户：" + username);
        UsersEntity byUsername = appLoginService.findByUsername(username);
        if (byUsername == null) {
            return R.error(500, "用户不存在");
        }
        return R.success(byUsername);
    }
}
