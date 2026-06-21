package com.TiendaVHS_RetroVideoManager.Usuarios.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    @NotBlank(message = "El campo 'run' no puede ser nulo")
    private String run;

    @NotBlank(message = "El campo 'nombres' no puede ser nulo")
    private String nombres;

    @NotBlank(message = "El campo 'email' no puede ser nulo")
    private String email;

    @NotBlank(message = "El campo 'apellidos' no puede ser nulo")
    private String apellidos;

    @NotBlank(message = "El campo 'telefonoFijo' no puede ser nulo")
    private String telefonoFijo;

    @NotBlank(message = "El campo 'direccion' no puede ser nulo")
    private String direccion;

    @NotBlank(message = "El campo 'telefonoMovil' no puede ser nulo")
    private String telefonoMovil;
}
