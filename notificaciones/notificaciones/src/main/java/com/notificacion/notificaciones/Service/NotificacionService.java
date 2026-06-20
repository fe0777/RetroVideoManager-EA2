package com.notificacion.notificaciones.Service;

import com.notificacion.notificaciones.DTO.NotificacionRequestDTO;
import com.notificacion.notificaciones.DTO.NotificacionResponseDTO;
import com.notificacion.notificaciones.Model.Notificacion;
import com.notificacion.notificaciones.Repository.NotificacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository repository;

    public List<NotificacionResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<NotificacionResponseDTO> getById(Long id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public NotificacionResponseDTO save(NotificacionRequestDTO request) {
        Notificacion n = toEntity(request);
        return toResponse(repository.save(n));
    }

    public NotificacionResponseDTO update(Long id, NotificacionRequestDTO request) {
        Notificacion n = repository.findById(id)
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

    private Notificacion toEntity(NotificacionRequestDTO request) {
        Notificacion n = new Notificacion();
        n.setDestinatario(request.getDestinatario());
        n.setMensaje(request.getMensaje());
        n.setTipo(request.getTipo());
        n.setLeida(request.getLeida());
        return n;
    }

    private NotificacionResponseDTO toResponse(Notificacion n) {
        NotificacionResponseDTO res = new NotificacionResponseDTO();
        res.setId(n.getId());
        res.setDestinatario(n.getDestinatario());
        res.setMensaje(n.getMensaje());
        res.setTipo(n.getTipo());
        res.setLeida(n.getLeida());
        return res;
    }
}
