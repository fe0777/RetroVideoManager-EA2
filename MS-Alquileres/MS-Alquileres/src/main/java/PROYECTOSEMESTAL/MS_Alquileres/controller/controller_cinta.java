package PROYECTOSEMESTAL.MS_Alquileres.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import PROYECTOSEMESTAL.MS_Alquileres.Modelo.cinta;
import PROYECTOSEMESTAL.MS_Alquileres.service.Service_cinta;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@RequestMapping("/api/cintas")
@RequiredArgsConstructor
public class controller_cinta {
    

    @Autowired 
    private Service_cinta service;

    @GetMapping
    public ResponseEntity<List<cinta>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<cinta> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)                   
                .orElse(ResponseEntity.notFound().build()); 
    }

    
    @PostMapping
    public ResponseEntity<cinta> crear(@Valid @RequestBody cinta cinta) {
        cinta nueva = service.guardar(cinta);
        return ResponseEntity.status(201).body(nueva);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<cinta> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody cinta cintaActualizada) {
        return service.obtenerPorId(id)
                .map(existente -> {
                    return ResponseEntity.ok(service.guardar(cintaActualizada));
                })
                .orElse(ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build(); 
    }
}
