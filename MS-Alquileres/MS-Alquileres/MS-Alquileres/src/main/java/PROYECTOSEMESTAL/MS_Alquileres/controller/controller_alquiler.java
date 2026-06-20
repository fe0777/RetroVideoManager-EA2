package PROYECTOSEMESTAL.MS_Alquileres.controller;


import java.util.List;

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

import PROYECTOSEMESTAL.MS_Alquileres.DTO.alquilerDTOresponse;
import PROYECTOSEMESTAL.MS_Alquileres.service.Service_alquiler;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alquileres")
@RequiredArgsConstructor
public class controller_alquiler {
 @Autowired
    private Service_alquiler serviceAlquiler;

    @GetMapping
    public ResponseEntity<List<alquilerDTOresponse>> listar(){
        return ResponseEntity.ok(serviceAlquiler.findAll());
    }
    
    @PostMapping
    public ResponseEntity<alquilerDTOresponse> guardar(@RequestBody alquilerDTOresponse alquiler){
        alquilerDTOresponse nuevoAlquiler = serviceAlquiler.save(alquiler);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlquiler);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<alquilerDTOresponse> buscar(@PathVariable Long id){
        try {
            alquilerDTOresponse alquiler = serviceAlquiler.findById(id);
            return ResponseEntity.ok(alquiler);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<alquilerDTOresponse> actualizar(@PathVariable Long id, @RequestBody alquilerDTOresponse alquiler){
        try {
            serviceAlquiler.findById(id);
            alquilerDTOresponse alquilerActualizado = serviceAlquiler.update(id, alquiler);
            return ResponseEntity.ok(alquilerActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try {
            serviceAlquiler.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
