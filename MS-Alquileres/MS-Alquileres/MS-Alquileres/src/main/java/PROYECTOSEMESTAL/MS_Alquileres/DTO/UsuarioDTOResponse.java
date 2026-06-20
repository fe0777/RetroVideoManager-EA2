package PROYECTOSEMESTAL.MS_Alquileres.DTO;

import lombok.Data;

@Data
public class UsuarioDTOResponse {
    private Long usuario_id;
    private String nombreUsuario;
    private String correo;
    private String rol;
    private String contraseña;
}
