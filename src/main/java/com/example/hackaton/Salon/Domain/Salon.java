package com.example.hackaton.Salon.Domain;

import com.example.hackaton.Reserva.Domain.Reserva;
import com.example.hackaton.Etiqueta.Domain.Etiqueta;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalon;

    private String nombre;

    private String ubicacion;

    private int capacidad;

    private String descripcion;

    @OneToMany(mappedBy = "salon", cascade = CascadeType.ALL)
    private Set<Reserva> reservas;

    @ManyToMany
    @JoinTable(
            name = "SalonEtiqueta",
            joinColumns = @JoinColumn(name = "idSalon"),
            inverseJoinColumns = @JoinColumn(name = "idEtiqueta")
    )
    private Set<Etiqueta> etiquetas;

    // Getters y Setters
}

