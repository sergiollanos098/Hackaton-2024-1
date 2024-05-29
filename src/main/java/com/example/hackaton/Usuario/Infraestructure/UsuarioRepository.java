package com.example.hackaton.Usuario.Infraestructure;

import com.example.hackaton.Usuario.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoelectronico(String email);
}
