package com.proyect.retrovideo.manager.Ventas.service;

import com.proyect.retrovideo.manager.Ventas.dto.VentaRequestDTO;
import com.proyect.retrovideo.manager.Ventas.dto.VentaResponseDTO;
import com.proyect.retrovideo.manager.Ventas.model.VentaModel;
import com.proyect.retrovideo.manager.Ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<VentaResponseDTO> findAll() {
        return ventaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VentaResponseDTO findById(Long id) {
        return ventaRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public List<VentaResponseDTO> findByUsuarioId(Long usuarioId) {
        return ventaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<VentaResponseDTO> findByPeliculaId(Long peliculaId) {
        return ventaRepository.findByPeliculaId(peliculaId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VentaResponseDTO save(VentaRequestDTO dto) {
        VentaModel venta = new VentaModel();
        venta.setUsuarioId(dto.getUsuarioId());
        venta.setPeliculaId(dto.getPeliculaId());
        venta.setTituloPelicula(dto.getTituloPelicula());
        venta.setCantidad(dto.getCantidad());
        venta.setPrecioUnitario(dto.getPrecioUnitario());
        venta.setTotal(dto.getCantidad() * dto.getPrecioUnitario());
        venta.setFechaVenta(LocalDateTime.now());
        venta.setEstado("COMPLETADA");
        return toDTO(ventaRepository.save(venta));
    }

    public VentaResponseDTO update(Long id, VentaRequestDTO dto) {
        return ventaRepository.findById(id).map(venta -> {
            venta.setUsuarioId(dto.getUsuarioId());
            venta.setPeliculaId(dto.getPeliculaId());
            venta.setTituloPelicula(dto.getTituloPelicula());
            venta.setCantidad(dto.getCantidad());
            venta.setPrecioUnitario(dto.getPrecioUnitario());
            venta.setTotal(dto.getCantidad() * dto.getPrecioUnitario());
            return toDTO(ventaRepository.save(venta));
        }).orElse(null);
    }

    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }

    private VentaResponseDTO toDTO(VentaModel venta) {
        VentaResponseDTO dto = new VentaResponseDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setUsuarioId(venta.getUsuarioId());
        dto.setPeliculaId(venta.getPeliculaId());
        dto.setTituloPelicula(venta.getTituloPelicula());
        dto.setCantidad(venta.getCantidad());
        dto.setPrecioUnitario(venta.getPrecioUnitario());
        dto.setTotal(venta.getTotal());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setEstado(venta.getEstado());
        return dto;
    }
}
