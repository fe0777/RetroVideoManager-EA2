package com.ProyectoFullStack.Autenticacion.RepositoryAutenticacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoFullStack.Autenticacion.Entity.UsuarioEntity;

@Repository
public interface UserRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}
