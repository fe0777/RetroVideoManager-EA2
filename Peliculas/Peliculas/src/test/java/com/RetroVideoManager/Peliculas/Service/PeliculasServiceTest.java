package com.RetroVideoManager.Peliculas.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.RetroVideoManager.Peliculas.DTO.peliculasRequestDTO;
import com.RetroVideoManager.Peliculas.DTO.peliculasResponseDTO;
import com.RetroVideoManager.Peliculas.Model.peliculasModel;
import com.RetroVideoManager.Peliculas.Repository.peliculasRepository;
import com.RetroVideoManager.Peliculas.Service.peliculasService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PeliculasServiceTest {

@Mock
private peliculasRepository repository;

@InjectMocks
private peliculasService service;

private peliculasModel pelicula;

@BeforeEach
void setUp() {
    pelicula = new peliculasModel();
    pelicula.setId(1L);
    pelicula.setTitulo("Titanic");
    pelicula.setDirector("James Cameron");
    pelicula.setAnio(1997);
    pelicula.setGenero("Drama");
    pelicula.setSinopsis("Historia del Titanic");
}

@Test
void testGetAll() {
    when(repository.findAll()).thenReturn(Arrays.asList(pelicula));

    List<peliculasResponseDTO> resultado = service.getAll();

    assertEquals(1, resultado.size());
    assertEquals("Titanic", resultado.get(0).getTitulo());
}

@Test
void testGetById() {
    when(repository.findById(1L)).thenReturn(Optional.of(pelicula));

    Optional<peliculasResponseDTO> resultado = service.getById(1L);

    assertTrue(resultado.isPresent());
    assertEquals("Titanic", resultado.get().getTitulo());
}

@Test
void testSave() {
    peliculasRequestDTO dto = new peliculasRequestDTO(
            "Titanic",
            "James Cameron",
            1997,
            "Drama",
            "Historia del Titanic"
    );

    when(repository.save(any(peliculasModel.class)))
            .thenReturn(pelicula);

    peliculasResponseDTO resultado = service.save(dto);

    assertNotNull(resultado);
    assertEquals("Titanic", resultado.getTitulo());
}

@Test
void testDelete() {
    doNothing().when(repository).deleteById(1L);

    service.delete(1L);

    verify(repository, times(1)).deleteById(1L);
}
}
