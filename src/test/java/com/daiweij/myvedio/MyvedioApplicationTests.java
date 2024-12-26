package com.daiweij.myvedio;

import com.daiweij.myvedio.modules.thirdparty.service.ThirdPartyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyvedioApplicationTests {
    @Autowired
    private ThirdPartyService thirdPartyService;

    @Test
    void contextLoads() {
    }

    @Test
    void testSendSmsCode() {
        thirdPartyService.sendVerificationCode("15549201757");
        System.out.println("发送成功");
    }

}
