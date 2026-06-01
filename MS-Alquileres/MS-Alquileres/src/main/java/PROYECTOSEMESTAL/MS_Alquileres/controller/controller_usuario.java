package PROYECTOSEMESTAL.MS_Alquileres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PROYECTOSEMESTAL.MS_Alquileres.DTO.UsuarioDTOResponse;
import PROYECTOSEMESTAL.MS_Alquileres.service.Service_usuario;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class controller_usuario {

    @Autowired
    private Service_usuario service;

    @GetMapping
    public ResponseEntity<List<UsuarioDTOResponse>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTOResponse> findById(@PathVariable Long id) {
        try {
            UsuarioDTOResponse usuario = service.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTOResponse> crear(
            @Valid @RequestBody UsuarioDTOResponse usuario) {

        UsuarioDTOResponse nuevo = service.save(usuario);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTOResponse> actualizar(
            @PathVariable Long id,
            @RequestBody UsuarioDTOResponse usuario) {

        try {
            UsuarioDTOResponse usuarioActualizado = service.update(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}