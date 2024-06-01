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


    public Reserva(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Usuario usuario, Salon salon) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.usuario = usuario;
        this.salon = salon;
    }


// Getters y Setters
}
