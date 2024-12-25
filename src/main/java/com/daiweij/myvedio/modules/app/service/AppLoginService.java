package com.daiweij.myvedio.modules.app.service;

import com.daiweij.myvedio.modules.app.form.LoginRequest;
import com.daiweij.myvedio.modules.app.form.LoginResponse;

public interface AppLoginService {
    LoginResponse login(LoginRequest loginRequest);
}
