package com.membresias.membresias.DTO;

import jakarta.validation.constraints.NotBlank;   
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MembresiaRequestDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;

    @NotBlank(message = "El tipo de membresía es obligatorio")
    private String tipoMembresia;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;

    @NotNull(message = "La duración es obligatoria")
    @Positive(message = "La duracion debe ser mayor a cero")
    private Integer duracionDias;

    @Size(max = 500, message = "maximo 500 caracteres")
    private String beneficios;
}


