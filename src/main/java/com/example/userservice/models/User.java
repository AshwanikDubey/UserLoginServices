package com.example.userservice.models;

import ch.qos.logback.core.joran.action.BaseModelAction;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import java.util.List;

@Data
@Entity
public class User extends BaseModel {
    private String username;
    private String email;
    private String hashedPassword;
    private boolean isEmailVerified;
    @ManyToMany
    private List<Role> roles;

}
