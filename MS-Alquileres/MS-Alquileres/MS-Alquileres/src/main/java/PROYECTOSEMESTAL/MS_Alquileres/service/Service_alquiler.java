package PROYECTOSEMESTAL.MS_Alquileres.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PROYECTOSEMESTAL.MS_Alquileres.Modelo.alquiler;
import PROYECTOSEMESTAL.MS_Alquileres.DTO.alquilerDTOresponse;
import PROYECTOSEMESTAL.MS_Alquileres.repository.repository_alquiler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class Service_alquiler {
    
    @Autowired
    private repository_alquiler repository;
    
    public List<alquilerDTOresponse> findAll(){ 
        return repository.findAll().stream()
            .map(alquiler -> {
                alquilerDTOresponse DTO = new alquilerDTOresponse();
                DTO.setId(alquiler.getId());
                DTO.setFechaDevolucion(alquiler.getFechaDevolucion());
                DTO.setFechaAlquiler(alquiler.getFechaAlquiler());
                DTO.setMulta(alquiler.getMulta());
                DTO.setDevuelto(alquiler.getDevuelto());
                return DTO;
            }).collect(Collectors.toList());
    }
  public alquilerDTOresponse findById(Long id) {
        return repository.findById(id)
                .map(alquiler -> {
                alquilerDTOresponse DTO = new alquilerDTOresponse();
                DTO.setFechaDevolucion(alquiler.getFechaDevolucion());
                DTO.setFechaAlquiler(alquiler.getFechaAlquiler());
                DTO.setMulta(alquiler.getMulta());
                DTO.setDevuelto(alquiler.getDevuelto());
                return DTO;
                })
                .orElse(null);
            }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public alquilerDTOresponse save(alquilerDTOresponse alquiler) {
        alquiler nuevoalquiler = new alquiler();
        nuevoalquiler.setFechaDevolucion(alquiler.getFechaDevolucion());
        nuevoalquiler.setFechaAlquiler(alquiler.getFechaAlquiler());
        nuevoalquiler.setMulta(alquiler.getMulta());
        nuevoalquiler.setDevuelto(alquiler.getDevuelto());
        return toResponse(repository.save(nuevoalquiler));
    }

    private alquilerDTOresponse toResponse(alquiler alquiler) {
        alquilerDTOresponse dto = new alquilerDTOresponse();
        dto.setId(alquiler.getId());
        dto.setFechaDevolucion(alquiler.getFechaDevolucion());
        dto.setFechaAlquiler(alquiler.getFechaAlquiler());
        dto.setMulta(alquiler.getMulta());
        dto.setDevuelto(alquiler.getDevuelto());
        return dto;
    }

    public alquilerDTOresponse update(Long id, alquilerDTOresponse alquiler) {
        return repository.findById(id)
                .map(existingAlquiler -> {
                    existingAlquiler.setFechaDevolucion(alquiler.getFechaDevolucion());
                    existingAlquiler.setFechaAlquiler(alquiler.getFechaAlquiler());
                    existingAlquiler.setMulta(alquiler.getMulta());
                    existingAlquiler.setDevuelto(alquiler.getDevuelto());
                    return toResponse(repository.save(existingAlquiler));
                })
                .orElse(null);
    }


}
