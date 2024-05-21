package com.example.hackaton.Etiqueta.Domain;

import com.example.hackaton.Salon.Domain.Salon;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtiqueta;

    private String nombre;

    @ManyToMany
    private Set<Salon> salones;

    // Getters y Setters
}
