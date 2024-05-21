package com.example.hackaton.Etiqueta.Domain;
import com.example.hackaton.Etiqueta.Infraestructure.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    public List<Etiqueta> getAllEtiquetas() {
        return etiquetaRepository.findAll();
    }

    public Etiqueta createEtiqueta(Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }

    public Etiqueta updateEtiqueta(Long id, Etiqueta updatedEtiqueta) {
        Etiqueta etiqueta = etiquetaRepository.findById(id).orElseThrow(() -> new RuntimeException("Etiqueta not found"));
        etiqueta.setNombre(updatedEtiqueta.getNombre());
        return etiquetaRepository.save(etiqueta);
    }

    @Transactional
    public void deleteEtiqueta(Long id) {
        etiquetaRepository.deleteById(id);
    }
}
