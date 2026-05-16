package PROYECTOSEMESTAL.MS_Alquileres.DTO;


import PROYECTOSEMESTAL.MS_Alquileres.Modelo.cinta;
import PROYECTOSEMESTAL.MS_Alquileres.Modelo.usuario;
import lombok.Data;

@Data
public class alquilerDTOresponse {
    private Long id;
    private usuario Usuario;
    private cinta cinta;

    private String fechaAlquiler;

    private String fechaDevolucion;

    private String fechaLimite;

    private String devuelto;

    private Double multa;


}
