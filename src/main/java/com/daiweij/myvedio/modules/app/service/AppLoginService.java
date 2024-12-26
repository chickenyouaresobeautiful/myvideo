package com.daiweij.myvedio.modules.app.service;

import com.daiweij.myvedio.modules.app.dto.LoginRequest;
import com.daiweij.myvedio.modules.app.dto.LoginResponse;
import com.daiweij.myvedio.modules.app.dto.RegisterRequest;
import com.daiweij.myvedio.modules.sys.entity.UsersEntity;

import java.util.Map;

public interface AppLoginService {
    LoginResponse login(LoginRequest loginRequest);

    void register(RegisterRequest registerRequest);

    UsersEntity findByUsername(String username);
}
