package com.membresias.membresias.Service;

import com.membresias.membresias.Model.Membresia;
import com.membresias.membresias.Repository.MembresiaRepository;
import com.membresias.membresias.DTO.MembresiaRequestDTO;
import com.membresias.membresias.DTO.MembresiaResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembresiaService {

    @Autowired
    private MembresiaRepository repository;

    
    public List<MembresiaResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<MembresiaResponseDTO> getById(Long id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

   
    public MembresiaResponseDTO save(MembresiaRequestDTO request) {
        try {
            Membresia m = toEntity(request);
            return toResponse(repository.save(m));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la membresía");
        }
    }

   
    public MembresiaResponseDTO update(Long id, MembresiaRequestDTO request) {

        Membresia m = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));

        m.setNombreUsuario(request.getNombreUsuario());
        m.setTipoMembresia(request.getTipoMembresia());
        m.setPrecio(request.getPrecio());
        m.setDuracionDias(request.getDuracionDias());
        m.setBeneficios(request.getBeneficios());

        return toResponse(repository.save(m));
    }

    
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Membresía no encontrada");
        }
        repository.deleteById(id);
    }

    
    private Membresia toEntity(MembresiaRequestDTO request) {
        Membresia m = new Membresia();
        m.setNombreUsuario(request.getNombreUsuario());
        m.setTipoMembresia(request.getTipoMembresia());
        m.setPrecio(request.getPrecio());
        m.setDuracionDias(request.getDuracionDias());
        m.setBeneficios(request.getBeneficios());
        return m;
    }

   
    private MembresiaResponseDTO toResponse(Membresia m) {
        MembresiaResponseDTO res = new MembresiaResponseDTO();
        res.setId(m.getId());
        res.setNombreUsuario(m.getNombreUsuario());
        res.setTipoMembresia(m.getTipoMembresia());
        res.setPrecio(m.getPrecio());
        res.setDuracionDias(m.getDuracionDias());
        res.setBeneficios(m.getBeneficios());
        return res;
    }
}