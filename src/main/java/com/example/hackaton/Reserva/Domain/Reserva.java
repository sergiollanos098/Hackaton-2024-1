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

    public Reserva(Salon salon, LocalTime horaFin, Usuario usuario, LocalTime horaInicio, Long id, LocalDate fecha) {
        this.salon = salon;
        this.horaFin = horaFin;
        this.usuario = usuario;
        this.horaInicio = horaInicio;
        this.id = id;
        this.fecha = fecha;
    }

    public Reserva() {
            this.id = id;
            this.fecha = fecha;
            this.horaInicio = horaInicio;
            this.horaFin = horaFin;
            this.usuario = usuario;
            this.salon = salon;
        }

    public Reserva(LocalDate of, LocalTime of1, LocalTime of2, Usuario usuario, Salon salon) {
    }
}

    // Getters y Setters

