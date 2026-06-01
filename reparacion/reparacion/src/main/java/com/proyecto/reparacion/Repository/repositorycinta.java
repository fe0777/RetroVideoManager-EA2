package com.proyecto.reparacion.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.reparacion.model.Cintas;

@Repository
public interface repositorycinta extends JpaRepository<Cintas, Long> {
}