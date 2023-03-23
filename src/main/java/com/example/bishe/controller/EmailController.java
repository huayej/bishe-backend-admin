package com.example.bishe.controller;


import com.example.bishe.utils.SendMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    SendMsgUtil sendMsgUtil;

    @PostMapping("/verifySend")
    @ResponseBody
    public Boolean verifySend(@RequestBody Map map){
        System.out.println(map.get("code").toString());
        System.out.println(map.get("email").toString());
       return sendMsgUtil.contextLoads(map.get("code").toString(),map.get("email").toString());
    }
}
