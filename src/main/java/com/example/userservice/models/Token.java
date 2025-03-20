package com.example.userservice.models;

import jakarta.persistence.ManyToOne;

public class Token extends BaseModel{
    private String value;
    @ManyToOne
    private User user;
    private long expiryAt;
}
