package com.example.hackaton.auth.DTOS;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterReq {

    private String nombre;
    private String correoelectronico;
    private String password;
    private String telefono;
    private Boolean isAdmin=false;

}