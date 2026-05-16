package com.ProyectoFullStack.Autenticacion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegisterDTO {
    private String username;
    private String email;
}
