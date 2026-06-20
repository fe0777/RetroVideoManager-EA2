package com.membresias.membresias.ServiceTest;

import com.membresias.membresias.DTO.MembresiaRequestDTO;
import com.membresias.membresias.Model.Membresia;
import com.membresias.membresias.Repository.MembresiaRepository;
import com.membresias.membresias.Service.MembresiaService;
import com.membresias.membresias.DTO.MembresiaResponseDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MembresiasServiceTest {

    @Mock
    private MembresiaRepository repository;

    @InjectMocks
    private MembresiaService service;

    private Membresia membresia;
    private MembresiaRequestDTO request;

    @BeforeEach
    void setUp() {

        membresia = new Membresia();
        membresia.setId(1L);
        membresia.setNombreUsuario("Fernanda");
        membresia.setTipoMembresia("Premium");
        membresia.setPrecio(9990.0);
        membresia.setDuracionDias(30);
        membresia.setBeneficios("Acceso completo");

        request = new MembresiaRequestDTO();
        request.setNombreUsuario("Fernanda");
        request.setTipoMembresia("Premium");
        request.setPrecio(9990.0);
        request.setDuracionDias(30);
        request.setBeneficios("Acceso completo");
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(membresia));

        List<MembresiaResponseDTO> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Fernanda", result.get(0).getNombreUsuario());
    }

    @Test
    void testGetById() {
        when(repository.findById(1L)).thenReturn(Optional.of(membresia));

        Optional<MembresiaResponseDTO> result = service.getById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void testSave() {
        when(repository.save(any(Membresia.class))).thenReturn(membresia);

        MembresiaResponseDTO result = service.save(request);

        assertNotNull(result);
        assertEquals("Fernanda", result.getNombreUsuario());
    }

    @Test
    void testUpdate_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.update(1L, request));

        assertEquals("Membresía no encontrada", ex.getMessage());
    }

    @Test
    void testDelete() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.delete(1L));
    }
}
