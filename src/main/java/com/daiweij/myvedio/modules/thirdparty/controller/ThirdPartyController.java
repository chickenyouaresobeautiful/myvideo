package com.daiweij.myvedio.modules.thirdparty.controller;

import com.daiweij.myvedio.common.exception.CustomException;
import com.daiweij.myvedio.common.utils.R;
import com.daiweij.myvedio.modules.thirdparty.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/thirdparty")
public class ThirdPartyController {
    @Autowired
    private ThirdPartyService thirdPartyService;

    @PostMapping("/send-verification-code")
    public R<Map<String, Object>> sendVerificationCode(@RequestBody String phoneNumber) {
        try {
            thirdPartyService.sendVerificationCode(phoneNumber);
            return R.success();
        } catch (CustomException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }
}
