package com.example.SpringBootRestAWSS3.service;

import com.example.SpringBootRestAWSS3.model.User;
import com.example.SpringBootRestAWSS3.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserAuthRepository userAuthRepository;

    @Autowired
    public UserService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public User findByName(String name){
       User user = userAuthRepository.findByName(name);
       return user;
    }
}
