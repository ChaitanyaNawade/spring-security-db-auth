package com.chaitanya.SecurityImpl03.controller;

import com.chaitanya.SecurityImpl03.entity.MyUser;
import com.chaitanya.SecurityImpl03.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser myUser)
    {
        myUser.setPassword(encoder.encode(myUser.getPassword()));
        return userRepository.save(myUser);
    }
}
