package com.RetroVideoManager.Pago.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class pagoRequestDTO {

    @NotNull(message = "El ID de la película es obligatorio")
    private Long peliculaId;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    @NotNull(message = "El monto es obligatorio")
    private Double monto;

    private String nroTarjeta;
}
