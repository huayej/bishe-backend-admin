package com.example.bishe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Date;

public class SpringbootLoginDemo1ApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;


    void contextLoads(){
        SimpleMailMessage mailMessage = new SimpleMailMessage(); //创建一个邮件信息
        System.out.println("当前时间" + new Date());
        mailMessage.setSubject("你好啊");                        //邮件的主题
        mailMessage.setText("我是你新学习的Spring Boot邮件发送的知识");//邮件的内容
        mailMessage.setTo("verifysend@163.com");                        //发送者的邮箱
        mailMessage.setFrom("verifysend@163.com");                      //接收者的邮箱
        //我这里发送者和接收者都填写的是自己的邮箱

        mailSender.send(mailMessage);                                //发送邮件

    }
}
