package com.example.bishe;

import com.example.bishe.utils.SendMsgUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLoginDemo1ApplicationTestsTest {

    @Autowired
    SendMsgUtil sendMsgUtil;

    @Test
    void contextLoads(){
        sendMsgUtil.contextLoads("123","1002775866@qq.com");
    }
}