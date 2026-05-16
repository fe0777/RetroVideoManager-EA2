package com.RetroVideoManager.Pago.DTO;

import lombok.Data;

@Data
public class pagoResponseDTO {
    private Integer idPago;
    private Long peliculaId;
    private String peliculaTitulo;
    private Long usuarioId;
    private String usuarioNombres;
    private String username;
    private String metodoPago;
    private Double monto;
    private String fechaPago;
    private String estadoPago;
    private String nroTarjeta;
}
