package com.RetroVideoManager.Peliculas.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class peliculasRequestDTO {
    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 100)
    private String titulo;

    @NotBlank(message = "El director no puede estar vacío")
    @Size(max = 100)
    private String director;

    @NotNull(message = "El año es obligatorio")
    private Integer anio;

    @NotBlank(message = "El género no puede estar vacío")
    @Size(max = 50)
    private String genero;

    @NotBlank(message = "La sinopsis no puede estar vacía")
    @Size(max = 500)
    private String sinopsis;
}
