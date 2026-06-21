package com.TiendaVHS_RetroVideoManager.Usuarios.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioInternalRegisterDTO;
import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioRequestDTO;
import com.TiendaVHS_RetroVideoManager.Usuarios.DTO.UsuarioResponseDTO;
import com.TiendaVHS_RetroVideoManager.Usuarios.Model.ModelUsuarios;
import com.TiendaVHS_RetroVideoManager.Usuarios.Repository.RepositoryUsuarios;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceUsuarios {

    @Autowired
    private RepositoryUsuarios repositoryUsuarios;

    public List<UsuarioResponseDTO> findAll() {
        return repositoryUsuarios.findAll().stream().map(this::toResponse).toList();
    }

    public UsuarioResponseDTO findById(Long id) {
        return repositoryUsuarios.findById(id).map(this::toResponse).orElse(null);
    }

    public Optional<UsuarioResponseDTO> findByUsername(String username) {
        return repositoryUsuarios.findByUsername(username).map(this::toResponse);
    }

    public void deleteById(Long id) {
        repositoryUsuarios.deleteById(id);
    }

    public UsuarioResponseDTO save(UsuarioRequestDTO request) {
        ModelUsuarios nuevo = new ModelUsuarios();
        nuevo.setRun(request.getRun());
        nuevo.setNombres(request.getNombres());
        nuevo.setEmail(request.getEmail());
        nuevo.setApellidos(request.getApellidos());
        nuevo.setTelefonoFijo(request.getTelefonoFijo());
        nuevo.setDireccion(request.getDireccion());
        nuevo.setTelefonoMovil(request.getTelefonoMovil());
        return toResponse(repositoryUsuarios.save(nuevo));
    }

    /** Llamado internamente por Autenticación al momento del registro */
    public UsuarioResponseDTO createFromAuth(UsuarioInternalRegisterDTO dto) {
        if (repositoryUsuarios.findByUsername(dto.getUsername()).isPresent()) {
            return repositoryUsuarios.findByUsername(dto.getUsername()).map(this::toResponse).get();
        }
        ModelUsuarios nuevo = new ModelUsuarios();
        nuevo.setUsername(dto.getUsername());
        nuevo.setEmail(dto.getEmail());
        return toResponse(repositoryUsuarios.save(nuevo));
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO request) {
        return repositoryUsuarios.findById(id).map(existing -> {
            existing.setRun(request.getRun());
            existing.setNombres(request.getNombres());
            existing.setEmail(request.getEmail());
            existing.setApellidos(request.getApellidos());
            existing.setTelefonoFijo(request.getTelefonoFijo());
            existing.setDireccion(request.getDireccion());
            existing.setTelefonoMovil(request.getTelefonoMovil());
            return toResponse(repositoryUsuarios.save(existing));
        }).orElse(null);
    }

    private UsuarioResponseDTO toResponse(ModelUsuarios usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setRun(usuario.getRun());
        dto.setNombres(usuario.getNombres());
        dto.setEmail(usuario.getEmail());
        dto.setApellidos(usuario.getApellidos());
        dto.setTelefonoFijo(usuario.getTelefonoFijo());
        dto.setDireccion(usuario.getDireccion());
        dto.setTelefonoMovil(usuario.getTelefonoMovil());
        return dto;
    }

    // Otros métodos específicos de Hateos podrían ir aquí
    public Optional<UsuarioResponseDTO> findByEmail(String email) {
        return repositoryUsuarios.findByEmail(email).map(this::toResponse);
    }

    public Optional<UsuarioResponseDTO> findByRun(String run) {
        return repositoryUsuarios.findByRun(run).map(this::toResponse);
    }
}
