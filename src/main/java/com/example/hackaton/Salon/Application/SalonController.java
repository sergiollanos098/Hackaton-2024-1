package com.example.hackaton.Salon.Application;

import com.example.hackaton.Salon.Domain.Salon;
import com.example.hackaton.Salon.Domain.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salones")
public class SalonController {

    @Autowired
    private SalonService salonService;

    @GetMapping
    public ResponseEntity<List<Salon>> getAllSalones() {
        return ResponseEntity.ok(salonService.getAllSalones());
    }

    @PostMapping
    public ResponseEntity<Salon> createSalon(@RequestBody Salon salon) {
        return ResponseEntity.ok(salonService.createSalon(salon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salon> updateSalon(@PathVariable Long id, @RequestBody Salon salon) {
        return ResponseEntity.ok(salonService.updateSalon(id, salon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalon(@PathVariable Long id) {
        salonService.deleteSalon(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idSalon}/etiquetas/{idEtiqueta}")
    public ResponseEntity<Void> addEtiquetaToSalon(@PathVariable Long idSalon, @PathVariable Long idEtiqueta) {
        salonService.addEtiquetaToSalon(idSalon, idEtiqueta);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idSalon}/etiquetas/{idEtiqueta}")
    public ResponseEntity<Void> removeEtiquetaFromSalon(@PathVariable Long idSalon, @PathVariable Long idEtiqueta) {
        salonService.removeEtiquetaFromSalon(idSalon, idEtiqueta);
        return ResponseEntity.noContent().build();
    }
}

