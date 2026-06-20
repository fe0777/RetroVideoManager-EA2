package com.proyect.retrovideo.manager.Ventas.repository;

import com.proyect.retrovideo.manager.Ventas.model.VentaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<VentaModel, Long> {
    List<VentaModel> findByUsuarioId(Long usuarioId);
    List<VentaModel> findByPeliculaId(Long peliculaId);
}
