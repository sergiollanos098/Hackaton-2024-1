package com.example.hackaton.Reserva.Infraestructure;

import com.example.hackaton.Reserva.Domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
}
