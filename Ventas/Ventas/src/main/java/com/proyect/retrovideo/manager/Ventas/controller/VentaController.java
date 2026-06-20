package com.proyect.retrovideo.manager.Ventas.controller;

import com.proyect.retrovideo.manager.Ventas.dto.VentaRequestDTO;
import com.proyect.retrovideo.manager.Ventas.dto.VentaResponseDTO;
import com.proyect.retrovideo.manager.Ventas.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> findAll() {
        return ResponseEntity.ok(ventaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> findById(@PathVariable Long id) {
        VentaResponseDTO venta = ventaService.findById(id);
        if (venta == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<VentaResponseDTO>> findByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ventaService.findByUsuarioId(usuarioId));
    }

    @GetMapping("/pelicula/{peliculaId}")
    public ResponseEntity<List<VentaResponseDTO>> findByPeliculaId(@PathVariable Long peliculaId) {
        return ResponseEntity.ok(ventaService.findByPeliculaId(peliculaId));
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> save(@Valid @RequestBody VentaRequestDTO dto) {
        return ResponseEntity.status(201).body(ventaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody VentaRequestDTO dto) {
        VentaResponseDTO updated = ventaService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ventaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
