package com.RetroVideoManager.Peliculas.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class peliculasResponseDTO {
    private Long id;
    private String titulo;
    private String director;
    private Integer anio;
    private String genero;
    private String sinopsis;
}
