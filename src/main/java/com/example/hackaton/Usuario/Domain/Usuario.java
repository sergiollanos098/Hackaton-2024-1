package com.example.hackaton.Usuario.Domain;

import com.example.hackaton.Reserva.Domain.Reserva;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;

    private String correoElectronico;

    private String contrasena;

    private String telefono;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Reserva> reservas;

    // Getters y Setters
}

