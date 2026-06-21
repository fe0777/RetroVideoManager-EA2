package com.TiendaVHS_RetroVideoManager.Usuarios.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TiendaVHS_RetroVideoManager.Usuarios.Model.ModelUsuarios;

@Repository
public interface RepositoryUsuarios extends JpaRepository<ModelUsuarios, Long> {
    Optional<ModelUsuarios> findByUsername(String username);

    // Métodos adicionales para Hateos
    Optional<ModelUsuarios> findByEmail(String email);
    Optional<ModelUsuarios> findByRun(String run);
}
