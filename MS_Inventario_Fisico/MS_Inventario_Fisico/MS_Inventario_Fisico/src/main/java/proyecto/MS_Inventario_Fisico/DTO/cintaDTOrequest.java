package proyecto.MS_Inventario_Fisico.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class cintaDTOrequest {
    @NotNull(message = "El id de la película es obligatorio")
    @Positive(message = "El id de la película debe ser mayor a 0")
    private Long idPelicula;

    @NotBlank(message = "Disponible no puede estar vacío")
    private String disponible;

    @NotBlank(message = "Reservada no puede estar vacío")
    private String reservada;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 100)
    private String estado;

    @NotBlank(message = "La ubicación no puede estar vacía")
    @Size(max = 200)
    private String ubicacion;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private LocalDate fechaIngreso;

}
