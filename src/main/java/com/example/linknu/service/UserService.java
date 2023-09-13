package com.example.linknu.service;

import com.example.linknu.dto.User;
import com.example.linknu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
