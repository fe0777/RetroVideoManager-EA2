package com.notificacion.notificaciones.Controller;

import com.notificacion.notificaciones.DTO.NotificacionRequestDTO;
import com.notificacion.notificaciones.DTO.NotificacionResponseDTO;
import com.notificacion.notificaciones.Service.NotificacionService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService service;

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> create(
            @RequestBody NotificacionRequestDTO request) {

        NotificacionResponseDTO nueva = service.save(request);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody NotificacionRequestDTO request) {

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