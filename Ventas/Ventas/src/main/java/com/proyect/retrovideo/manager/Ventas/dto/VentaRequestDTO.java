package com.proyect.retrovideo.manager.Ventas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VentaRequestDTO {

    @NotNull(message = "El id del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El id de la pelicula es obligatorio")
    private Long peliculaId;

    @NotBlank(message = "El titulo de la pelicula es obligatorio")
    private String tituloPelicula;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    private Double precioUnitario;
}
