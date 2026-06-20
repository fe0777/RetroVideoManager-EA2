package com.ProyectoFullStack.Autenticacion.DTO;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String rol;

}
