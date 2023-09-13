package com.example.linknu.controller;

import com.example.linknu.service.EmailService;
import com.example.linknu.service.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EmailAuthController {

    private final EmailService emailService;
    private final UtilService utilService;
    private String authCode;

    //1. 유저 이메일을 받는다.
    //2. knu메일인지 체크한다.
    //3.
    @PostMapping("/emailAuth")
    public String emailAuth(
            @RequestParam("userEmail") String userEmail,
            Model model
    ) {

        if (emailService.knuCheck(userEmail)) {

            authCode = utilService.createRandomCode();
            emailService.sendAuthMail(authCode,userEmail);//인증이메일 보냄

            model.addAttribute("userEmail", userEmail);

            return "checkAuthCode";

        } else {
            return "notKnu";
        }


    }

    //유저가 입력한 인증코드가 맞는지 확인한다.
    @PostMapping("/checkAuthCode")
    public String checkAuthCode(
            @RequestParam("inputCode") String inputCode,
            @RequestParam("userEmail") String userEmail,
            Model model
    ) {
        System.out.println(inputCode + "  " + authCode);
        if (authCode.equals(inputCode)) {
            model.addAttribute("userEmail", userEmail);
            return "createAccountForm";
        } else return "checkAuthCode";
    }

    @GetMapping("/notKnu")
    public String notKnu() {
        return "notKnu";
    }
}
