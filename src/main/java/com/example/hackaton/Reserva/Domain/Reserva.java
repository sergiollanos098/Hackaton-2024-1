package com.example.hackaton.Reserva.Domain;

import com.example.hackaton.Salon.Domain.Salon;
import com.example.hackaton.Usuario.Domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idSalon", nullable = false)
    private Salon salon;

    // Getters y Setters
}
