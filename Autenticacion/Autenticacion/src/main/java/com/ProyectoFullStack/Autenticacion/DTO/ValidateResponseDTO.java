package com.ProyectoFullStack.Autenticacion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateResponseDTO {
    private boolean valid;
    private String username;
    private String rol;
}