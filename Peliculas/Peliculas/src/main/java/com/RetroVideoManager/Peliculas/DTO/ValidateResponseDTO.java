package com.RetroVideoManager.Peliculas.DTO;

import lombok.Data;

@Data
public class ValidateResponseDTO {
    private boolean valid;
    private String username;
    private String rol;
    
}
