package com.example.hackaton.auth.Utils;

import com.example.hackaton.Usuario.Domain.Role;
import com.example.hackaton.Usuario.Domain.Usuario;
import com.example.hackaton.Usuario.Domain.UsuarioService;
import com.example.hackaton.Usuario.Infraestructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorizationUtils {

    @Autowired
    private UsuarioService userService ;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean isAdminOrResourceOwner(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Optional<Usuario> usuario= usuarioRepository.findByCorreoelectronico(username);

        return usuario.get().getId().equals(id) || usuario.get().getRole().equals(Role.ADMIN);
        //dice si el id del parametro es igual del que la persona en el token o es admin
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername(); //retorna el email si lo encuentra
        }
        catch (ClassCastException e) {
            return null; //retorna null si no lo encuentra
        }
    }
//da el correo de la persona que esta en el token
}