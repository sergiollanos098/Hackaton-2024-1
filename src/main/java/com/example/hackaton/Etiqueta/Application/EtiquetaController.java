package com.example.hackaton.Etiqueta.Application;

import com.example.hackaton.Etiqueta.Domain.Etiqueta;
import com.example.hackaton.Etiqueta.Domain.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;

    @GetMapping
    public ResponseEntity<List<Etiqueta>> getAllEtiquetas() {
        return ResponseEntity.ok(etiquetaService.getAllEtiquetas());
    }

    @PostMapping
    public ResponseEntity<Etiqueta> createEtiqueta(@RequestBody Etiqueta etiqueta) {
        return ResponseEntity.ok(etiquetaService.createEtiqueta(etiqueta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etiqueta> updateEtiqueta(@PathVariable Long id, @RequestBody Etiqueta etiqueta) {
        return ResponseEntity.ok(etiquetaService.updateEtiqueta(id, etiqueta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtiqueta(@PathVariable Long id) {
        etiquetaService.deleteEtiqueta(id);
        return ResponseEntity.noContent().build();
    }
}

