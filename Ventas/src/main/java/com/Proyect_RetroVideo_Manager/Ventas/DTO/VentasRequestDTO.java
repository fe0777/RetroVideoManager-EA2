package com.Proyect_RetroVideo_Manager.Ventas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VentasRequestDTO {
    @NotBlank(message = "El producto es obligatorio")
    private String producto;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    private Double precioUnitario;

    @NotNull(message = "El total de la venta es obligatorio")
    private Double totalVenta;

    @NotBlank(message = "La fecha de la venta es obligatoria")
    private String fechaVenta;

    private Integer precio;
}
