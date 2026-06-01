package com.ProyectoFullStack.Autenticacion.ServiceAutenticacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoFullStack.Autenticacion.DTO.UserResponseDTO;
import com.ProyectoFullStack.Autenticacion.RepositoryAutenticacion.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<UserResponseDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    UserResponseDTO userResponseDTO = new UserResponseDTO();
                    userResponseDTO.setId(user.getId());
                    userResponseDTO.setUsername(user.getUsername());
                    userResponseDTO.setEmail(user.getEmail());
                    userResponseDTO.setRol(user.getRol().getName());
                    return userResponseDTO;
                });
    }
}
