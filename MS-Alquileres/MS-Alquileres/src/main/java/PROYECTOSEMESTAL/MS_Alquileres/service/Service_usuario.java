package PROYECTOSEMESTAL.MS_Alquileres.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PROYECTOSEMESTAL.MS_Alquileres.DTO.UsuarioDTOResponse;
import PROYECTOSEMESTAL.MS_Alquileres.repository.repository_usuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import PROYECTOSEMESTAL.MS_Alquileres.Modelo.usuario;

@Service
@Transactional
@RequiredArgsConstructor
public class Service_usuario {

    @Autowired
    repository_usuario repository;

    public List<UsuarioDTOResponse> findAll(){ 
        return repository.findAll().stream()
            .map(usuario -> {
                UsuarioDTOResponse DTO = new UsuarioDTOResponse();
                DTO.setUsuario_id(usuario.getUsuario_id());
                DTO.setNombreUsuario(usuario.getNombreUsuario());
                DTO.setCorreo(usuario.getCorreo());
                DTO.setContraseña(usuario.getContraseña());
                DTO.setRol(usuario.getRol());
                return DTO;
            }).collect(Collectors.toList());
    }

    public UsuarioDTOResponse findById(Long id) {
        return repository.findById(id)
                .map(usuario -> {
                    UsuarioDTOResponse dto = new UsuarioDTOResponse();
                    dto.setUsuario_id(usuario.getUsuario_id());
                    dto.setNombreUsuario(usuario.getNombreUsuario());
                    dto.setCorreo(usuario.getCorreo());
                    dto.setContraseña(usuario.getContraseña());
                    dto.setRol(usuario.getRol());
                    return dto;
                })
                .orElse(null);
            }

    public UsuarioDTOResponse save(UsuarioDTOResponse usuario){
        usuario nuevoUsuario = new usuario();
        nuevoUsuario.setUsuario_id(usuario.getUsuario_id());
        nuevoUsuario.setContraseña(usuario.getContraseña());
        nuevoUsuario.setCorreo(usuario.getCorreo());
        nuevoUsuario.setNombreUsuario(usuario.getNombreUsuario());
        nuevoUsuario.setRol(usuario.getRol());
        return toResponse(repository.save(nuevoUsuario));
    }

    private UsuarioDTOResponse toResponse(usuario usuario) {
        UsuarioDTOResponse dto = new UsuarioDTOResponse();
        dto.setUsuario_id(usuario.getUsuario_id());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setCorreo(usuario.getCorreo());
        dto.setContraseña(usuario.getContraseña());
        dto.setRol(usuario.getRol());
        return dto;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public UsuarioDTOResponse update(Long id, UsuarioDTOResponse usuario) {
        return repository.findById(id)
                .map(existingUsuario -> {
                    existingUsuario.setNombreUsuario(usuario.getNombreUsuario());
                    existingUsuario.setCorreo(usuario.getCorreo());
                    existingUsuario.setContraseña(usuario.getContraseña());
                    existingUsuario.setRol(usuario.getRol());
                    return toResponse(repository.save(existingUsuario));
                })
                .orElse(null);
    }
}
