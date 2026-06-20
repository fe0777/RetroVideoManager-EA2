package com.proyecto.reparacion.controller;

import java.util.List;

import com.proyecto.reparacion.DTO.cintaDTOresponse;
import com.proyecto.reparacion.Service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cintas")
public class controller {
    @Autowired
    private service serviceCintas;

    @GetMapping
    public ResponseEntity<List<cintaDTOresponse>> listar(){
        return ResponseEntity.ok(serviceCintas.findAll());
    }
    
    @PostMapping
    public ResponseEntity<cintaDTOresponse> guardar(@RequestBody cintaDTOresponse cinta){
        cintaDTOresponse nuevaCinta = serviceCintas.save(cinta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCinta);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<cintaDTOresponse> buscar(@PathVariable Long id){
        try {
            cintaDTOresponse cinta = serviceCintas.findById(id);
            return ResponseEntity.ok(cinta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<cintaDTOresponse> actualizar(@PathVariable Long id, @RequestBody cintaDTOresponse cinta){
        try {
            serviceCintas.findById(id);
            cintaDTOresponse cintaActualizada = serviceCintas.update(id, cinta);
            return ResponseEntity.ok(cintaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try {
            serviceCintas.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

