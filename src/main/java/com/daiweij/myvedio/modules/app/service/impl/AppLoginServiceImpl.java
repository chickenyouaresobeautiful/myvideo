package com.daiweij.myvedio.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daiweij.myvedio.common.exception.CustomException;
import com.daiweij.myvedio.common.utils.JwtUtils;
import com.daiweij.myvedio.modules.app.form.LoginRequest;
import com.daiweij.myvedio.modules.app.form.LoginResponse;
import com.daiweij.myvedio.modules.app.service.AppLoginService;
import com.daiweij.myvedio.modules.sys.entity.UsersEntity;
import com.daiweij.myvedio.modules.sys.service.UsersService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.daiweij.myvedio.common.utils.Constant.LoginType;

@Service
public class AppLoginServiceImpl implements AppLoginService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // 根据账号类型查询用户
        UsersEntity loginUser = queryByAccountType(loginRequest.getLoginType(), loginRequest.getUsername());
        // 校验密码
        if (!loginUser.getPassword().equals(DigestUtils.sha256Hex(loginRequest.getPassword()))) {
            throw new CustomException("Password error");
        }
        // 生成token
        String token = jwtUtils.generateToken(loginUser.getId(), loginUser.getUsername());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setMessage("login successful");
        response.setUser(loginUser);

        return response;
    }

    private UsersEntity queryByAccountType(int loginType, String account) {
        LoginType type = LoginType.getTypeByValue(loginType);
        if (type == null) {
            throw new CustomException("Unsupported login type: " + loginType);
        }
        UsersEntity loginUser = new UsersEntity();
        loginUser = switch (type) {
            case USERNAME -> usersService.getOne(new QueryWrapper<>(loginUser).eq("username", account));
            case PHONENUMBER -> usersService.getOne(new QueryWrapper<>(loginUser).eq("phone_number", account));
        };
        if (loginUser == null) {
            throw new CustomException("User not found");
        }
        return loginUser;
    }
}
