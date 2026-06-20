package com.proyecto.reparacion.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class cintaDTORequest {


    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 200)
    private String descripcion;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 50)
    private String estado;

    @NotNull(message = "El costo es obligatorio")
    @Positive(message = "El costo debe ser mayor a 0")
    private Double costo;
}

