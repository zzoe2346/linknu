package com.example.linknu.controller;

import com.example.linknu.dto.User;
import com.example.linknu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/emailAuthForm")
    public String emailAuthForm() {

        return "emailAuthForm";
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
}
