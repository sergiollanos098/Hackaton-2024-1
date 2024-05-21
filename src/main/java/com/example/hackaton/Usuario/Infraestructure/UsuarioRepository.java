package com.example.hackaton.Usuario.Infraestructure;

import com.example.hackaton.Usuario.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByCorreoElectronico(String correo);
}
