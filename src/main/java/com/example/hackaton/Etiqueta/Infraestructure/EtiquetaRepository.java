package com.example.hackaton.Etiqueta.Infraestructure;

import com.example.hackaton.Etiqueta.Domain.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtiquetaRepository extends JpaRepository<Etiqueta,Long> {
}
