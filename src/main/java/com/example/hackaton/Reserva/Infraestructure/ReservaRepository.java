package com.example.hackaton.Reserva.Infraestructure;

import com.example.hackaton.Reserva.Domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findAllByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
