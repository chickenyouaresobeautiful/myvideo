package com.daiweij.myvedio.modules.app.controller;

import com.daiweij.myvedio.common.exception.CustomException;
import com.daiweij.myvedio.common.utils.Constant;
import com.daiweij.myvedio.common.utils.R;
import com.daiweij.myvedio.common.validator.ValidatorUtils;
import com.daiweij.myvedio.modules.app.form.LoginRequest;
import com.daiweij.myvedio.modules.app.form.LoginResponse;
import com.daiweij.myvedio.modules.app.service.AppLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class AppLoginController {
    private Logger logger = LoggerFactory.getLogger(AppLoginController.class);

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
            logger.error(e.getMessage());
            return R.error(e.getCode(), e.getMsg());
        }
    }
}
