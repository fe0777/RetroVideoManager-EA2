package com.Proyect_RetroVideo_Manager.Ventas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.Proyect_RetroVideo_Manager.Ventas.DTO.VentasRequestDTO;
import com.Proyect_RetroVideo_Manager.Ventas.DTO.VentasResponseDTO;
import com.Proyect_RetroVideo_Manager.Ventas.Model.Ventas;
import com.Proyect_RetroVideo_Manager.Ventas.Repository.RepositoryVentas;
@Service
@Transactional
public class ServiceVentas {
    @Autowired
    private RepositoryVentas repositoryVentas;

    public List<VentasResponseDTO> findAll() {
        return repositoryVentas.findAll().stream()
                .map(venta -> {
                    VentasResponseDTO dto = new VentasResponseDTO();
                    dto.setId(venta.getId());
                    dto.setProducto(venta.getProducto());
                    dto.setCantidad(venta.getCantidad());
                    dto.setPrecioUnitario(venta.getPrecioUnitario());
                    dto.setTotalVenta(venta.getTotalVenta());
                    dto.setFechaVenta(venta.getFechaVenta());
                    dto.setPrecio(venta.getPrecio());
                    return dto;
                })
                .toList();
    }

    public VentasResponseDTO findById(Long id) {
        return repositoryVentas.findById(id)
                .map(venta -> {
                    VentasResponseDTO dto = new VentasResponseDTO();
                    dto.setId(venta.getId());
                    dto.setProducto(venta.getProducto());
                    dto.setCantidad(venta.getCantidad());
                    dto.setPrecioUnitario(venta.getPrecioUnitario());
                    dto.setTotalVenta(venta.getTotalVenta());
                    dto.setFechaVenta(venta.getFechaVenta());
                    dto.setPrecio(venta.getPrecio());
                    return dto;
                })
                .orElse(null);
    }

    public List<VentasResponseDTO> findById(String producto) {
        return repositoryVentas.findByProducto(producto).stream()
                .map(venta -> {
                    VentasResponseDTO dto = new VentasResponseDTO();
                    dto.setId(venta.getId());
                    dto.setProducto(venta.getProducto());
                    dto.setCantidad(venta.getCantidad());
                    dto.setPrecioUnitario(venta.getPrecioUnitario());
                    dto.setTotalVenta(venta.getTotalVenta());
                    dto.setFechaVenta(venta.getFechaVenta());
                    dto.setPrecio(venta.getPrecio());
                    return dto;
                })
                .toList();
    }

    public void deleteById(Long id) {
        repositoryVentas.deleteById(id);
    }

    public VentasResponseDTO save(VentasRequestDTO venta) {
        Ventas savedVenta = new Ventas();
        savedVenta.setProducto(venta.getProducto());
        savedVenta.setCantidad(venta.getCantidad());
        savedVenta.setPrecioUnitario(venta.getPrecioUnitario());
        savedVenta.setTotalVenta(venta.getTotalVenta());
        savedVenta.setFechaVenta(venta.getFechaVenta());
        savedVenta.setPrecio(venta.getPrecio());
        return toResponse(repositoryVentas.save(savedVenta));
    }

    private VentasResponseDTO toResponse(Ventas v) {
    VentasResponseDTO r = new VentasResponseDTO();
        r.setId(v.getId());
        r.setProducto(v.getProducto());
        r.setCantidad(v.getCantidad());
        r.setPrecioUnitario(v.getPrecioUnitario());
        r.setTotalVenta(v.getTotalVenta());
        r.setFechaVenta(v.getFechaVenta());
        r.setPrecio(v.getPrecio());
        return r;
    }

    public VentasResponseDTO update(Long id, VentasRequestDTO venta) {
        return repositoryVentas.findById(id)
                .map(existingVenta -> {
                    existingVenta.setProducto(venta.getProducto());
                    existingVenta.setCantidad(venta.getCantidad());
                    existingVenta.setPrecioUnitario(venta.getPrecioUnitario());
                    existingVenta.setTotalVenta(venta.getTotalVenta());
                    existingVenta.setFechaVenta(venta.getFechaVenta());
                    existingVenta.setPrecio(venta.getPrecio());
                    return toResponse(repositoryVentas.save(existingVenta));
                })
                .orElse(null);
    }
}
