package com.ProyectoFullStack.Autenticacion.ServiceAutenticacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ProyectoFullStack.Autenticacion.DTO.AuthResponseDTO;
import com.ProyectoFullStack.Autenticacion.DTO.LoginRequestDTO;
import com.ProyectoFullStack.Autenticacion.DTO.RegisterRequestDTO;
import com.ProyectoFullStack.Autenticacion.DTO.UsuarioRegisterDTO;
import com.ProyectoFullStack.Autenticacion.DTO.ValidateResponseDTO;
import com.ProyectoFullStack.Autenticacion.Entity.RolEntity;
import com.ProyectoFullStack.Autenticacion.Entity.UsuarioEntity;
import com.ProyectoFullStack.Autenticacion.RepositoryAutenticacion.RolRepository;
import com.ProyectoFullStack.Autenticacion.RepositoryAutenticacion.UserRepository;
import com.ProyectoFullStack.Autenticacion.Util.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class authService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Qualifier("usuariosClient")
    private WebClient usuariosClient;

    public AuthResponseDTO authenticate(LoginRequestDTO loginRequest) {
        UsuarioEntity user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRol().getName());
        return new AuthResponseDTO(token, user.getUsername(), user.getRol().getName());
    }

    public AuthResponseDTO registre(RegisterRequestDTO registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        RolEntity rol = rolRepository.findByName(registerRequest.getRol())
                .orElseGet(() -> {
                    RolEntity newRol = new RolEntity();
                    newRol.setName(registerRequest.getRol());
                    return rolRepository.save(newRol);
                });

        UsuarioEntity newUser = new UsuarioEntity();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setRol(rol);
        newUser.setEmail(registerRequest.getEmail());
        userRepository.save(newUser);

        // Crear perfil básico en el servicio Usuarios
        try {
            UsuarioRegisterDTO perfilBasico = new UsuarioRegisterDTO(
                    registerRequest.getUsername(),
                    registerRequest.getEmail());
            usuariosClient.post()
                    .uri("/api/v1/usuarios/internal/register")
                    .bodyValue(perfilBasico)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (Exception e) {
            // El registro de credenciales ya fue exitoso; el perfil puede completarse después
        }

        String token = jwtUtil.generateToken(newUser.getUsername(), rol.getName());
        return new AuthResponseDTO(token, newUser.getUsername(), rol.getName());
    }

    public ValidateResponseDTO validateToken(String token) {
        if (!jwtUtil.isTokenValid(token)) {
            throw new RuntimeException("Token inválido o expirado");
        }
        String username = jwtUtil.extractUsername(token);
        String role = jwtUtil.extractRole(token);
        return new ValidateResponseDTO(true, username, role);
    }
}
