package com.Proyect_RetroVideo_Manager.Ventas.DTO;

import lombok.Data;

@Data
public class VentasResponseDTO {
    private Long id;
    private String producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double totalVenta;
    private String fechaVenta;
    private Integer precio;
}
