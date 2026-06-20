package com.RetroVideoManager.Peliculas.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.RetroVideoManager.Peliculas.DTO.peliculasRequestDTO;
import com.RetroVideoManager.Peliculas.DTO.peliculasResponseDTO;
import com.RetroVideoManager.Peliculas.Service.peliculasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/peliculas")
@Tag(
    name = "Películas",
    description = "Operaciones relacionadas con la gestión de películas"
)
public class peliculasController {

    @Autowired
    private peliculasService service;

    @Operation(
        summary = "Obtener todas las películas",
        description = "Retorna una lista con todas las películas registradas"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido correctamente"
    )
    @GetMapping
    public List<peliculasResponseDTO> getAll() {
        return service.getAll();
    }

    @Operation(
        summary = "Buscar película por ID",
        description = "Obtiene una película según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Película encontrada"),
        @ApiResponse(responseCode = "404", description = "Película no encontrada")
    })
    @GetMapping("/{id}")
    public Optional<peliculasResponseDTO> getById(
            @Parameter(description = "ID de la película")
            @PathVariable Long id) {

        return service.getById(id);
    }

    @Operation(
        summary = "Crear película",
        description = "Registra una nueva película"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Película creada correctamente"
    )
    @PostMapping
    public peliculasResponseDTO create(
            @Valid @RequestBody peliculasRequestDTO pelicula) {

        return service.save(pelicula);
    }

    @Operation(
        summary = "Actualizar película",
        description = "Actualiza una película existente"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Película actualizada correctamente"
    )
    @PutMapping("/{id}")
    public peliculasResponseDTO update(
            @Parameter(description = "ID de la película")
            @PathVariable Long id,

            @Valid @RequestBody peliculasRequestDTO pelicula) {

        return service.update(id, pelicula);
    }

    @Operation(
        summary = "Eliminar película",
        description = "Elimina una película por su ID"
    )
    @ApiResponse(
        responseCode = "204",
        description = "Película eliminada correctamente"
    )
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID de la película")
            @PathVariable Long id) {

        service.delete(id);
    }
}
