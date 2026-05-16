package PROYECTOSEMESTAL.MS_Alquileres.DTO;


import PROYECTOSEMESTAL.MS_Alquileres.Modelo.cinta;
import PROYECTOSEMESTAL.MS_Alquileres.Modelo.usuario;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class alquilerDTOrequest {
    @NotNull(message = "El ID del usuario es obligatorio")
    @Min(value = 1, message = "El ID del usuario debe ser válido")
    private usuario Usuario;

    @NotNull(message = "La cinta es obligatoria")
    @Min(value = 1, message = "La cinta debe ser válida")
    private cinta cinta;

    @NotNull(message = "La fecha de alquiler es obligatoria")
    private String fechaAlquiler;

    @NotNull(message = "La fecha de devolución es obligatoria")
    private String fechaDevolucion;

    @NotNull(message = "La fecha límite es obligatoria")
    private String fechaLimite;

    @NotNull(message = "El estado de devolución es obligatorio")
    private String devuelto;

    @NotNull(message = "La multa es obligatoria")
    @Min(value = 0, message = "La multa no puede ser negativa")
    private Double multa;
}
