package com.example.linknu.service;

import com.example.linknu.Entity.User;
import com.example.linknu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;
    public boolean checkAccount(String email, String password) {
        Optional<User> byEmailAndPassword = userRepository.findByEmailAndPassword(email, password);
        if (byEmailAndPassword.isPresent()){
            return true;
        } else return false;
    }

}
