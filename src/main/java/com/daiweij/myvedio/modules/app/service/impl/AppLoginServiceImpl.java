package com.daiweij.myvedio.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daiweij.myvedio.common.exception.CustomException;
import com.daiweij.myvedio.common.utils.JwtUtils;
import com.daiweij.myvedio.modules.app.dto.LoginRequest;
import com.daiweij.myvedio.modules.app.dto.LoginResponse;
import com.daiweij.myvedio.modules.app.dto.RegisterRequest;
import com.daiweij.myvedio.modules.app.service.AppLoginService;
import com.daiweij.myvedio.modules.sys.entity.UsersEntity;
import com.daiweij.myvedio.modules.sys.service.UsersService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.daiweij.myvedio.common.utils.Constant.LoginType;

@Service
public class AppLoginServiceImpl implements AppLoginService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // 根据账号类型查询用户
        UsersEntity loginUser = queryByAccountType(loginRequest.getLoginType(), loginRequest.getUsername());
        // 校验密码
        if (!loginUser.getPassword().equals(DigestUtils.sha256Hex(loginRequest.getPassword()))) {
            throw new CustomException("Password error");
        }
        // 生成token
        String token = JwtUtils.generateToken(loginUser.getId(), loginUser.getUsername());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setMessage("login successful");
        response.setUser(loginUser);

        return response;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(registerRequest.getUsername());
        usersEntity.setPassword(DigestUtils.sha256Hex(registerRequest.getPassword()));
        usersEntity.setPhoneNumber(registerRequest.getPhoneNumber());
        String realSmsCode = (String) redisTemplate.opsForValue().get(registerRequest.getPhoneNumber());
        if (!registerRequest.getVerificationCode().equals(realSmsCode)) {
            throw new CustomException("Verification code error");
        }
        usersService.save(usersEntity);
    }

    @Override
    public UsersEntity findByUsername(String username) {
        return usersService.getOne(new QueryWrapper<UsersEntity>().eq("username", username));
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
