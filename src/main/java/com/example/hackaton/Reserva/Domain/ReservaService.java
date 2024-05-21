package com.example.hackaton.Reserva.Domain;

import com.example.hackaton.Reserva.Infraestructure.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva updateReserva(Long id, Reserva updatedReserva) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva not found"));
        reserva.setFecha(updatedReserva.getFecha());
        reserva.setHoraInicio(updatedReserva.getHoraInicio());
        reserva.setHoraFin(updatedReserva.getHoraFin());
        return reservaRepository.save(reserva);
    }

    @Transactional
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}

