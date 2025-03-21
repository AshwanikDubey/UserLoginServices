package com.example.userservice.services;

import com.example.userservice.models.User;
import com.example.userservice.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;

    private UserService (UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User signUp(String name , String email , String password){
        User user = new User();
        user.setEmail(email);
        user.setUsername(name);
        user.setHashedPassword(password);

        return userRepo.save(user);
    }
}
