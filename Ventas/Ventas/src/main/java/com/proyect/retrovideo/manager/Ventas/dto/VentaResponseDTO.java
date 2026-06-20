package com.proyect.retrovideo.manager.Ventas.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VentaResponseDTO {
    private Long idVenta;
    private Long usuarioId;
    private Long peliculaId;
    private String tituloPelicula;
    private Integer cantidad;
    private Double precioUnitario;
    private Double total;
    private LocalDateTime fechaVenta;
    private String estado;
}
