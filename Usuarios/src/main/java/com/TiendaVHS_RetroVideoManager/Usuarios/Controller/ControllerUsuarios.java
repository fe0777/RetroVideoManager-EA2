package com.TiendaVHS_RetroVideoManager.Usuarios.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioInternalRegisterDTO;
import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioRequestDTO;
import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioResponseDTO;
import com.TiendaVHS_RetroVideoManager.Usuarios.Service.ServiceUsuarios;

@RestController
@RequestMapping("/api/v1/usuarios")
public class ControllerUsuarios {

    @Autowired
    private ServiceUsuarios serviceUsuarios;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(serviceUsuarios.findAll());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> guardar(@RequestBody UsuarioRequestDTO usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceUsuarios.save(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable Long id) {
        UsuarioResponseDTO usuario = serviceUsuarios.findById(id);
        if (usuario == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorUsername(@PathVariable String username) {
        return serviceUsuarios.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Endpoint interno: Autenticación lo llama al registrar un nuevo usuario */
    @PostMapping("/internal/register")
    public ResponseEntity<UsuarioResponseDTO> registroInterno(@RequestBody UsuarioInternalRegisterDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceUsuarios.createFromAuth(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable Long id,
            @RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO actualizado = serviceUsuarios.update(id, usuario);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            serviceUsuarios.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
