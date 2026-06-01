package PROYECTOSEMESTAL.MS_Alquileres.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioDTORequest {
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuario_id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;

    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contraseña;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;
    
}
