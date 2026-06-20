package com.proyecto.reparacion.Service;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.proyecto.reparacion.DTO.cintaDTOresponse;
import com.proyecto.reparacion.Repository.repositorycinta;
import com.proyecto.reparacion.model.Cintas;

import lombok.RequiredArgsConstructor;
@Service
@Transactional
@RequiredArgsConstructor
public class service {

    private final repositorycinta repository;

    public List<cintaDTOresponse> findAll(){
        return repository.findAll().stream()
            .map(cinta -> {
                cintaDTOresponse DTO = new cintaDTOresponse();
                DTO.setId(cinta.getId());
                DTO.setDescripcion(cinta.getDescripcion());
                DTO.setEstado(cinta.getEstado());
                DTO.setCosto(cinta.getCosto());
                return DTO;
            }).collect(Collectors.toList());
    }
    public cintaDTOresponse findById(Long id) {
        return repository.findById(id)
                .map(cinta -> {
                    cintaDTOresponse DTO = new cintaDTOresponse();
                    DTO.setId(cinta.getId());
                    DTO.setDescripcion(cinta.getDescripcion());
                    DTO.setEstado(cinta.getEstado());
                    DTO.setCosto(cinta.getCosto());
                    return DTO;
                })
                .orElse(null);
            }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public cintaDTOresponse save(cintaDTOresponse cinta) {
        Cintas nuevocinta = new Cintas();
        nuevocinta.setDescripcion(cinta.getDescripcion());
        nuevocinta.setEstado(cinta.getEstado());
        nuevocinta.setCosto(cinta.getCosto());
        return toResponse (repository.save(nuevocinta));
    }

    private cintaDTOresponse toResponse(Cintas cinta) {
        cintaDTOresponse dto = new cintaDTOresponse();
        dto.setId(cinta.getId());
        dto.setDescripcion(cinta.getDescripcion());
        dto.setEstado(cinta.getEstado());
        dto.setCosto(cinta.getCosto());
        return dto;
    }

    public cintaDTOresponse update(Long id, cintaDTOresponse cinta) {
        return repository.findById(id)
                .map(existingCinta -> {
                    existingCinta.setDescripcion(cinta.getDescripcion());
                    existingCinta.setEstado(cinta.getEstado());
                    existingCinta.setCosto(cinta.getCosto());
                    return toResponse(repository.save(existingCinta));
                })
                .orElse(null);
    }
    }





