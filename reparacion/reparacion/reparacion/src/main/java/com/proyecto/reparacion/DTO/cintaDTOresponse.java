package com.proyecto.reparacion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class cintaDTOresponse {

    private Long id;
    private String descripcion;
    private String estado;
    private Double costo;
}

