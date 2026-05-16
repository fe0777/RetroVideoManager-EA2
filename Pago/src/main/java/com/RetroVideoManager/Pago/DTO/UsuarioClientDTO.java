package com.RetroVideoManager.Pago.DTO;

import lombok.Data;

@Data
public class UsuarioClientDTO {
    private Long id;
    private String username;
    private String run;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefonoMovil;
    private String direccion;
}
