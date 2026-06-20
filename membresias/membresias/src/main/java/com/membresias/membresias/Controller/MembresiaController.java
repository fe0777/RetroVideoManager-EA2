package com.membresias.membresias.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.membresias.membresias.DTO.MembresiaRequestDTO;
import com.membresias.membresias.DTO.MembresiaResponseDTO;
import com.membresias.membresias.Service.MembresiaService;

@RestController
@RequestMapping("/membresias")
public class MembresiaController {

    @Autowired
    private MembresiaService service;

    @GetMapping
    public ResponseEntity<List<MembresiaResponseDTO>>getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembresiaResponseDTO> getById(@PathVariable Long id) {
        return service.getById(id)
        .map(ResponseEntity:: ok)
        .orElse(ResponseEntity.notFound().build());
    }

  
    @PostMapping
    public ResponseEntity <MembresiaResponseDTO> create(
           @Valid @RequestBody MembresiaRequestDTO request) {

        MembresiaResponseDTO nueva= service.save(request);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity <MembresiaResponseDTO> update(
          @PathVariable Long id,
          @Valid @RequestBody MembresiaRequestDTO request) {

        try {
            MembresiaResponseDTO actualizada = service.update(id,request);
            return ResponseEntity.ok(actualizada);
       }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
       }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id ) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

