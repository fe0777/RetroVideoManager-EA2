package com.RetroVideoManager.Pago.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.RetroVideoManager.Pago.DTO.pagoRequestDTO;
import com.RetroVideoManager.Pago.DTO.pagoResponseDTO;
import com.RetroVideoManager.Pago.Service.pagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pagos")
public class pagoController {

    @Autowired
    private pagoService pagoService;

    @GetMapping
    public ResponseEntity<List<pagoResponseDTO>> findAll() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<pagoResponseDTO> findById(@PathVariable Integer id) {
        pagoResponseDTO pago = pagoService.findById(id);
        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago);
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody pagoRequestDTO pagoRequestDTO) {
        try {
            pagoResponseDTO saved = pagoService.save(pagoRequestDTO, token);
            return ResponseEntity.status(201).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<pagoResponseDTO> update(@PathVariable Integer id,
            @RequestBody pagoRequestDTO pagoRequestDTO) {
        pagoResponseDTO updated = pagoService.update(id, pagoRequestDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        pagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
