package com.example.linknu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean knuCheck(String mail) {
        for (String s : mail.split("@")) {
            System.out.println(s);

        }



        if (mail.split("@")[1].equals("knu.ac.kr")) {
            return true;
        } else {
            return false;
        }
    }

    public void sendAuthMail(String code,String userEmail) {
        //단순 문자 메일 보낼수 있는 객체 생성
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("LinKnu 회원가입 인증메일입니다");
        message.setText("인증코드 입니다. "+ code);
        message.setTo(userEmail);

        javaMailSender.send(message);
    }

    public void sendHtmlEmail() {

    }


}
