package com.example.hackaton.auth.Domain;

import com.example.hackaton.Usuario.Domain.Role;
import com.example.hackaton.Usuario.Domain.Usuario;
import com.example.hackaton.Usuario.Infraestructure.UsuarioRepository;
import com.example.hackaton.auth.DTOS.JwtAuthResponse;
import com.example.hackaton.auth.DTOS.LoginReq;
import com.example.hackaton.auth.DTOS.RegisterReq;
import com.example.hackaton.config.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthService(UsuarioRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = new ModelMapper();
    }

    public JwtAuthResponse login(LoginReq req) {
        Optional<Usuario> user;
        user = userRepository.findByCorreoelectronico(req.getEmail());

        if (user.isEmpty()) throw new UsernameNotFoundException("Email is not registered");

        if (!passwordEncoder.matches(req.getPassword(), user.get().getPassword()))
            throw new IllegalArgumentException("Password is incorrect");

        JwtAuthResponse response = new JwtAuthResponse();

        response.setToken(jwtService.generateToken(user.get()));
        return response;
    }

    public JwtAuthResponse register(RegisterReq req){
        Optional<Usuario> user = userRepository.findByCorreoelectronico(req.getCorreoelectronico());
        if (user.isPresent()) throw new RuntimeException("Email is already registered");

        if (req.getIsAdmin()) {
            Usuario admin = new Usuario();
            admin.setRole(Role.ADMIN);
            admin.setNombre(req.getNombre());
            admin.setTelefono(req.getTelefono());
            admin.setCorreoelectronico(req.getCorreoelectronico());
            admin.setPassword(passwordEncoder.encode(req.getPassword()));


            userRepository.save(admin);

            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwtService.generateToken(admin));
            return response;
        }
        else {
            Usuario usuario = new Usuario();
            usuario.setRole(Role.USER);
            usuario.setNombre(req.getNombre());
            usuario.setTelefono(req.getTelefono());
            usuario.setCorreoelectronico(req.getCorreoelectronico());
            usuario.setPassword(passwordEncoder.encode(req.getPassword()));

            userRepository.save(usuario);

            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwtService.generateToken(usuario));
            return response;
        }

    }
}