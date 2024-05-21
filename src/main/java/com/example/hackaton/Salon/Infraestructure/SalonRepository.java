package com.example.hackaton.Salon.Infraestructure;

import com.example.hackaton.Salon.Domain.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalonRepository extends JpaRepository<Salon,Long> {
}
