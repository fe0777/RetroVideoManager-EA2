package com.RetroVideoManager.notificaciones.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class notificacionResponseDTO {
    private Long id;
    private String destinatario;
    private String mensaje;
    private String tipo;
    private Boolean leida;
}
