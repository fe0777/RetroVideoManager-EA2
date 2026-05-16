package com.RetroVideoManager.Pago.DTO;

import lombok.Data;

@Data
public class PeliculaClientDTO {
    private Long id;
    private String titulo;
    private String director;
    private Integer anio;
    private String genero;
    private String sinopsis;
}
