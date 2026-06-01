package proyecto.MS_Inventario_Fisico.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import proyecto.MS_Inventario_Fisico.DTO.cintaDTOresponse;
import proyecto.MS_Inventario_Fisico.Repository.CintaRepository;
import proyecto.MS_Inventario_Fisico.model.Cinta;

@RequiredArgsConstructor
@Service
@Transactional
public class CintaService {
private final CintaRepository repository;

    public List<cintaDTOresponse> findAll(){
        return repository.findAll().stream()
            .map(cinta -> {
                cintaDTOresponse DTO = new cintaDTOresponse();
                DTO.setId(cinta.getId());
                DTO.setIdPelicula(cinta.getIdPelicula());
                DTO.setDisponible(cinta.getDisponible());
                DTO.setReservada(cinta.getReservada());
                DTO.setEstado(cinta.getEstado());
                DTO.setUbicacion(cinta.getUbicacion());
                DTO.setFechaIngreso(cinta.getFechaIngreso());
                return DTO;
            }).collect(Collectors.toList());
    }
    public cintaDTOresponse findById(Long id) {
        return repository.findById(id)
                .map(cinta -> {
                    cintaDTOresponse DTO = new cintaDTOresponse();
                    DTO.setId(cinta.getId());
                    DTO.setIdPelicula(cinta.getIdPelicula());
                    DTO.setDisponible(cinta.getDisponible());
                    DTO.setReservada(cinta.getReservada());
                    DTO.setEstado(cinta.getEstado());
                    DTO.setUbicacion(cinta.getUbicacion());
                    DTO.setFechaIngreso(cinta.getFechaIngreso());
                    return DTO;
                })
                .orElse(null);
            }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public cintaDTOresponse save(cintaDTOresponse cinta) {
        Cinta nuevacinta = new Cinta();
        nuevacinta.setIdPelicula(cinta.getIdPelicula());
        nuevacinta.setDisponible(cinta.getDisponible());
        nuevacinta.setReservada(cinta.getReservada());
        nuevacinta.setEstado(cinta.getEstado());
        nuevacinta.setUbicacion(cinta.getUbicacion());
        nuevacinta.setFechaIngreso(cinta.getFechaIngreso());
        return toResponse (repository.save(nuevacinta));
    }

    private cintaDTOresponse toResponse(Cinta cinta) {
        cintaDTOresponse DTO = new cintaDTOresponse();
        DTO.setId(cinta.getId());
        DTO.setIdPelicula(cinta.getIdPelicula());
        DTO.setDisponible(cinta.getDisponible());
        DTO.setReservada(cinta.getReservada());
        DTO.setEstado(cinta.getEstado());
        DTO.setUbicacion(cinta.getUbicacion());
        DTO.setFechaIngreso(cinta.getFechaIngreso());
        return DTO;
    }

    public cintaDTOresponse update(Long id, cintaDTOresponse cinta) {
        return repository.findById(id)
                .map(existingCinta -> {
                    existingCinta.setIdPelicula(cinta.getIdPelicula());
                    existingCinta.setDisponible(cinta.getDisponible());
                    existingCinta.setReservada(cinta.getReservada());
                    existingCinta.setEstado(cinta.getEstado());
                    existingCinta.setUbicacion(cinta.getUbicacion());
                    existingCinta.setFechaIngreso(cinta.getFechaIngreso());
                    return toResponse(repository.save(existingCinta));
                })
                .orElse(null);
    }
   
}