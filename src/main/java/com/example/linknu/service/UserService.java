package com.example.linknu.service;

import com.example.linknu.dto.User;
import com.example.linknu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired //ㅅㅂ 이걸 안했네
    UserRepository userRepository;


    public void register(User user) {
        com.example.linknu.Entity.User userEntity = new com.example.linknu.Entity.User();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());

        System.out.println(userEntity.getEmail());
        System.out.println("he?");
        userRepository.save(userEntity);
    }

    public User getUser(String email){
        Optional<com.example.linknu.Entity.User> user0 = userRepository.findById(email);
        com.example.linknu.Entity.User user1 = user0.get();
        User user = new User(user1.getName(), user1.getEmail(), user1.getPassword());
        return user;

    }
}
