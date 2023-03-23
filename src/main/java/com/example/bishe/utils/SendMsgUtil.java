package com.example.bishe.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SendMsgUtil {
    @Autowired
    JavaMailSender javaMailSender;
    public Boolean contextLoads(String code,String email){
        SimpleMailMessage mailMessage = new SimpleMailMessage(); //创建一个邮件信息
        System.out.println("当前时间" + new Date());
        mailMessage.setSubject("验证码");                        //邮件的主题
        mailMessage.setText("你的验证码为"+code);//邮件的内容
        mailMessage.setTo(email);                        //发送者的邮箱
        mailMessage.setFrom("verifysend@163.com");                      //接收者的邮箱
        //我这里发送者和接收者都填写的是自己的邮箱
        try {
              javaMailSender.send(mailMessage);                                //发送邮件
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }


    }

}
