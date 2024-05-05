package com.example.linknu.controller;

import com.example.linknu.dto.LoginInfo;
import com.example.linknu.dto.User;
import com.example.linknu.service.LoginService;
import com.example.linknu.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginService loginService;


    @GetMapping("/emailAuthForm")
    public String emailAuthForm() {

        return "emailAuthForm";
    }
    @GetMapping("/ok")
    public String alertOk() {

        return "taxiPartyList";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("userEmail") String email,
            @RequestParam("name") String name,
            @RequestParam("password") String password

    ) {
        User user = new User(name, email, password);
        userService.register(user);
        return "redirect:/";
    }


    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession httpSession
            ) {

        //1 email이 있는지 확인
        //2 있으면 loginService(email,
        if(loginService.checkAccount(email,password)){

            User user = userService.getUser(email);
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setUser(user);
            httpSession.setAttribute("loginInfo",loginInfo);

            return "main";


        }else {

            return "redirect:/";
        }

    }
}
