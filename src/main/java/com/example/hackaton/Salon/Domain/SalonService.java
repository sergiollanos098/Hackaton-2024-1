package com.example.hackaton.Salon.Domain;

import com.example.hackaton.Etiqueta.Domain.Etiqueta;
import com.example.hackaton.Etiqueta.Infraestructure.EtiquetaRepository;
import com.example.hackaton.Salon.Infraestructure.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalonService {

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    public List<Salon> getAllSalones() {
        return salonRepository.findAll();
    }

    public Salon createSalon(Salon salon) {
        return salonRepository.save(salon);
    }

    public Salon updateSalon(Long id, Salon updatedSalon) {
        Salon salon = salonRepository.findById(id).orElseThrow(() -> new RuntimeException("Salon not found"));
        salon.setNombre(updatedSalon.getNombre());
        salon.setUbicacion(updatedSalon.getUbicacion());
        salon.setCapacidad(updatedSalon.getCapacidad());
        salon.setDescripcion(updatedSalon.getDescripcion());
        return salonRepository.save(salon);
    }

    @Transactional
    public void deleteSalon(Long id) {
        salonRepository.deleteById(id);
    }

    @Transactional
    public void addEtiquetaToSalon(Long idSalon, Long  idEtiqueta) {
        Salon salon = salonRepository.findById(idSalon).orElseThrow(() -> new RuntimeException("Salon not found"));
        Etiqueta etiqueta = etiquetaRepository.findById(idEtiqueta).orElseThrow(() -> new RuntimeException("Etiqueta not found"));
        salon.getEtiquetas().add(etiqueta);
    }

    @Transactional
    public void removeEtiquetaFromSalon(Long idSalon, Long idEtiqueta) {
        Salon salon = salonRepository.findById(idSalon).orElseThrow(() -> new RuntimeException("Salon not found"));
        Etiqueta etiqueta = etiquetaRepository.findById(idEtiqueta).orElseThrow(() -> new RuntimeException("Etiqueta not found"));
        salon.getEtiquetas().remove(etiqueta);
    }
}

