package com.notificacion.notificaciones.ServiceTest;

import com.notificacion.notificaciones.DTO.NotificacionRequestDTO;
import com.notificacion.notificaciones.DTO.NotificacionResponseDTO;
import com.notificacion.notificaciones.Model.Notificacion;
import com.notificacion.notificaciones.Repository.NotificacionRepository;
import com.notificacion.notificaciones.Service.NotificacionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacionesServiceTest {

    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionService service;

    @Test
    void testGetAll() {

        Notificacion notificacion = new Notificacion(
                1L,
                "fernanda@test.com",
                "Mensaje de prueba",
                "EMAIL",
                false
        );

        when(repository.findAll()).thenReturn(List.of(notificacion));

        List<NotificacionResponseDTO> resultado = service.getAll();

        assertEquals(1, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetById() {

        Notificacion notificacion = new Notificacion(
                1L,
                "fernanda@test.com",
                "Mensaje",
                "EMAIL",
                false
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(notificacion));

        Optional<NotificacionResponseDTO> resultado = service.getById(1L);

        assertTrue(resultado.isPresent());
        verify(repository).findById(1L);
    }

    @Test
    void testDelete() {

        when(repository.existsById(1L))
                .thenReturn(true);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}
