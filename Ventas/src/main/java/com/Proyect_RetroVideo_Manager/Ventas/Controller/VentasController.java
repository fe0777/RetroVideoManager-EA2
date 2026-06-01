package com.Proyect_RetroVideo_Manager.Ventas.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Proyect_RetroVideo_Manager.Ventas.DTO.VentasRequestDTO;
import com.Proyect_RetroVideo_Manager.Ventas.DTO.VentasResponseDTO;
import com.Proyect_RetroVideo_Manager.Ventas.Service.ServiceVentas;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentasController {

    @Autowired
    private ServiceVentas serviceVentas;

    @GetMapping
    public ResponseEntity<List<VentasResponseDTO>> findAll() {
        return ResponseEntity.ok(serviceVentas.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentasResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceVentas.findById(id));
    }

    @GetMapping("/producto/{producto}")
    public ResponseEntity<List<VentasResponseDTO>> findByProducto(@PathVariable String producto) {
        return ResponseEntity.ok(serviceVentas.findById(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        serviceVentas.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<VentasResponseDTO> save(@RequestBody VentasRequestDTO venta) {
        return ResponseEntity.ok(serviceVentas.save(venta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentasResponseDTO> update(@PathVariable Long id, @RequestBody VentasRequestDTO venta) {
        return ResponseEntity.ok(serviceVentas.update(id, venta));
    }
}