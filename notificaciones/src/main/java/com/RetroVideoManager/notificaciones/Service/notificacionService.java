package com.RetroVideoManager.notificaciones.Service;

import com.RetroVideoManager.notificaciones.Model.notificacionModel;
import com.RetroVideoManager.notificaciones.Repository.notificacionRepository;
import com.RetroVideoManager.notificaciones.Service.notificacionService;
import com.RetroVideoManager.notificaciones.DTO.notificacionRequestDTO;
import com.RetroVideoManager.notificaciones.DTO.notificacionResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class notificacionService {
    @Autowired
    private notificacionRepository repository;

    public List<notificacionResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<notificacionResponseDTO> getById(Long id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public notificacionResponseDTO save(notificacionRequestDTO request) {
        notificacionModel n = toEntity(request);
        return toResponse(repository.save(n));
    }

    public notificacionResponseDTO update(Long id, notificacionRequestDTO request) {
        notificacionModel n = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        n.setDestinatario(request.getDestinatario());
        n.setMensaje(request.getMensaje());
        n.setTipo(request.getTipo());
        n.setLeida(request.getLeida());

        return toResponse(repository.save(n));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Notificación no encontrada");
        }
        repository.deleteById(id);
    }

    private notificacionModel toEntity(notificacionRequestDTO request) {
        notificacionModel n = new notificacionModel();
        n.setDestinatario(request.getDestinatario());
        n.setMensaje(request.getMensaje());
        n.setTipo(request.getTipo());
        n.setLeida(request.getLeida());
        return n;
    }

    private notificacionResponseDTO toResponse(notificacionModel n) {
        notificacionResponseDTO res = new notificacionResponseDTO();
        res.setId(n.getId());
        res.setDestinatario(n.getDestinatario());
        res.setMensaje(n.getMensaje());
        res.setTipo(n.getTipo());
        res.setLeida(n.getLeida());
        return res;
    }
}
