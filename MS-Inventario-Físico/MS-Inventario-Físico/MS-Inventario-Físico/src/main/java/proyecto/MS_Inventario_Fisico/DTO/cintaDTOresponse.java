package proyecto.MS_Inventario_Fisico.DTO;

import java.time.LocalDate;


import lombok.Data;
@Data
public class cintaDTOresponse {
    private Long id;
    private Long idPelicula; 
    private String disponible;
    private String reservada;
    private String estado;
    private String ubicacion;
    private LocalDate fechaIngreso;
}
