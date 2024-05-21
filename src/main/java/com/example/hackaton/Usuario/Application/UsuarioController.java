package com.example.hackaton.Usuario.Application;

import com.example.hackaton.Usuario.Domain.Usuario;
import com.example.hackaton.Usuario.Domain.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.register(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String correoElectronico, @RequestParam String contrasena) {
        return ResponseEntity.ok(usuarioService.login(correoElectronico, contrasena));
    }
}

