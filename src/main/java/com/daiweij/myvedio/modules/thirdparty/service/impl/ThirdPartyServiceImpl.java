package com.daiweij.myvedio.modules.thirdparty.service.impl;

import com.daiweij.myvedio.common.exception.CustomException;
import com.daiweij.myvedio.common.utils.HttpUtils;
import com.daiweij.myvedio.common.utils.LogUtil;
import com.daiweij.myvedio.modules.thirdparty.service.ThirdPartyService;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
    @Value("${sms.appcode}")
    private String appcode;

    @Value("${sms.smsSignId}")
    private String smsSignId;

    @Value("${sms.templateId}")
    private String templateId;

    @Value("${sms.host}")
    private String host;

    @Value("${sms.path}")
    private String path;

    @Value("${sms.expiry-time}")
    private int expiryTime; // 验证码有效时间，单位分钟

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void sendVerificationCode(String phoneNumber) {
        LogUtil.info(this.getClass(), "Sending SMS verification code to phone number: " + phoneNumber);
        String method = "POST";
        String smsCode = generateVerificationCode();
        saveVerificationCode(phoneNumber, smsCode);
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("mobile", phoneNumber);
        querys.put("param", "**code**:" + smsCode + ",**minute**:5");

        querys.put("smsSignId", smsSignId);
        querys.put("templateId", templateId);
        Map<String, String> bodys = new HashMap<>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从\r\n\t    \t* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java\r\n\t    \t* 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            LogUtil.info(this.getClass(), "SMS sent successfully, response: " + response.toString());
        } catch (Exception e) {
            LogUtil.error(this.getClass(), "Failed to send SMS");
            throw new CustomException("Failed to send SMS", e);
        }
    }

    /**
     * 生成验证码
     *
     * @return
     */
    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10)); // 生成6位数字验证码
        }
        return code.toString();
    }

    /**
     * 保存验证码到Redis
     */
    private void saveVerificationCode(String phoneNumber, String code) {
        redisTemplate.opsForValue().set(phoneNumber, code, expiryTime, TimeUnit.MINUTES); // 设置验证码及过期时间
    }

    /**
     * 验证验证码是否正确
     */
    public boolean verifyCode(String phoneNumber, String inputCode) {
        String storedCode = (String) redisTemplate.opsForValue().get(phoneNumber); // 从Redis获取存储的验证码
        return storedCode != null && storedCode.equals(inputCode);
    }

    // 删除验证码（可选，验证码验证后可以删除）
    public void deleteVerificationCode(String phoneNumber) {
        redisTemplate.delete(phoneNumber);
    }
}
