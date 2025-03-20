package com.example.userservice.controllers;

import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.services.UserService;
import com.example.userservice.models.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    public signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        User user = userService.signUp(signUpRequestDto.getName(),signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword());

    }

}
