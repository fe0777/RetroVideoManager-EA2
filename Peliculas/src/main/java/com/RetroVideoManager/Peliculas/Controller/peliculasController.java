package com.RetroVideoManager.Peliculas.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.RetroVideoManager.Peliculas.DTO.peliculasRequestDTO;
import com.RetroVideoManager.Peliculas.DTO.peliculasResponseDTO;
import com.RetroVideoManager.Peliculas.Service.peliculasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/peliculas")
public class peliculasController {
    @Autowired
    private peliculasService service;

    @GetMapping
    public List<peliculasResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<peliculasResponseDTO> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public peliculasResponseDTO create(@Valid @RequestBody peliculasRequestDTO pelicula) {
        return service.save(pelicula);
    }

    @PutMapping("/{id}")
    public peliculasResponseDTO update(@PathVariable Long id,
                                      @Valid @RequestBody peliculasRequestDTO pelicula) {
        return service.update(id, pelicula);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
