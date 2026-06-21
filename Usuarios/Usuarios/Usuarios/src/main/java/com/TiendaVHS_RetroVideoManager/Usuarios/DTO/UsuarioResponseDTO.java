package com.TiendaVHS_RetroVideoManager.Usuarios.DTO;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String username;
    private String run;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefonoFijo;
    private String direccion;
    private String telefonoMovil;
}
