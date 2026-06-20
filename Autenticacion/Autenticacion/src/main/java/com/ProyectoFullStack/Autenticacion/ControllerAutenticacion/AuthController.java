package com.ProyectoFullStack.Autenticacion.ControllerAutenticacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.ProyectoFullStack.Autenticacion.DTO.AuthResponseDTO;
import com.ProyectoFullStack.Autenticacion.DTO.LoginRequestDTO;
import com.ProyectoFullStack.Autenticacion.ServiceAutenticacion.authService;
import com.ProyectoFullStack.Autenticacion.DTO.RegisterRequestDTO;
import com.ProyectoFullStack.Autenticacion.DTO.ValidateResponseDTO;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private authService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
            AuthResponseDTO response = authService.authenticate(loginRequest);
            return ResponseEntity.ok(response);
        
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequest) {
            AuthResponseDTO response = authService.registre(registerRequest);
            return ResponseEntity.ok(response);
        
    }

    @GetMapping("/validate")
    public ResponseEntity<ValidateResponseDTO> validateToken(@RequestParam String token) {
            ValidateResponseDTO response = authService.validateToken(token);
            return ResponseEntity.ok(response);
    }


}