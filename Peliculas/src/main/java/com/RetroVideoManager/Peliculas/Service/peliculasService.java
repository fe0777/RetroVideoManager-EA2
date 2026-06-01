package com.RetroVideoManager.Peliculas.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RetroVideoManager.Peliculas.DTO.peliculasRequestDTO;
import com.RetroVideoManager.Peliculas.DTO.peliculasResponseDTO;

import com.RetroVideoManager.Peliculas.Model.peliculasModel;
import com.RetroVideoManager.Peliculas.Repository.peliculasRepository;

@Service
public class peliculasService {
    @Autowired
    private peliculasRepository repository;

    public List<peliculasResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<peliculasResponseDTO> getById(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO);
    }

    public peliculasResponseDTO save(peliculasRequestDTO dto) {
        peliculasModel pelicula = new peliculasModel();
        pelicula.setTitulo(dto.getTitulo());
        pelicula.setDirector(dto.getDirector());
        pelicula.setAnio(dto.getAnio());
        pelicula.setGenero(dto.getGenero());
        pelicula.setSinopsis(dto.getSinopsis());

        return toResponseDTO(repository.save(pelicula));
    }

    public peliculasResponseDTO update(Long id, peliculasRequestDTO dto) {
        return repository.findById(id).map(pelicula -> {
            pelicula.setTitulo(dto.getTitulo());
            pelicula.setDirector(dto.getDirector());
            pelicula.setAnio(dto.getAnio());
            pelicula.setGenero(dto.getGenero());
            pelicula.setSinopsis(dto.getSinopsis());

            return toResponseDTO(repository.save(pelicula));
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private peliculasResponseDTO toResponseDTO(peliculasModel pelicula) {
        return new peliculasResponseDTO(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getDirector(),
                pelicula.getAnio(),
                pelicula.getGenero(),
                pelicula.getSinopsis()
        );
    }
}
