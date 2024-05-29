package com.example.hackaton.auth.DTOS;

import lombok.Data;

@Data
public class LoginReq {

    private String email;
    private String password;

}