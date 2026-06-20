package com.membresias.membresias.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MembresiaResponseDTO {
    private Long id;

    private String nombreUsuario;
    private String tipoMembresia; 
    private Double precio;
    private Integer duracionDias;
    private String beneficios;
}
