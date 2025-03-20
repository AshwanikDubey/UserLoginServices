package com.example.userservice.dtos;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import lombok.Data;

import java.util.List;

@Data
public class UserResponceDto {
    private String name;
    private String email;
    private List<Role> roleList;

    public static UserResponceDto fromUser(User user) {
        UserResponceDto userResponceDto = new UserResponceDto();
        userResponceDto.setEmail(user.getEmail());
        userResponceDto.setName(user.getUsername());
        userResponceDto.setRoleList(user.getRoles());
        return userResponceDto;
    }

}
