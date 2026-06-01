package com.Proyect_RetroVideo_Manager.Ventas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Proyect_RetroVideo_Manager.Ventas.Model.Ventas;

@Repository
public interface RepositoryVentas extends JpaRepository<Ventas, Long>{
    List<Ventas> findByProducto(String producto);
}