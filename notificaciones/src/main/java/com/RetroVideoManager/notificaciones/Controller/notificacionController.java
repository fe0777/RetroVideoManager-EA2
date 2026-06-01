package com.RetroVideoManager.notificaciones.Controller;

import com.RetroVideoManager.notificaciones.DTO.notificacionRequestDTO;
import com.RetroVideoManager.notificaciones.DTO.notificacionResponseDTO;
import com.RetroVideoManager.notificaciones.Service.notificacionService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class notificacionController {
    @Autowired
    private notificacionService service;

    @GetMapping
    public ResponseEntity<List<notificacionResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<notificacionResponseDTO> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<notificacionResponseDTO> create(
            @Valid @RequestBody notificacionRequestDTO request) {

        notificacionResponseDTO nueva = service.save(request);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<notificacionResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody notificacionRequestDTO request) {

        try {
            return ResponseEntity.ok(service.update(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
