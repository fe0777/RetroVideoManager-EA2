package com.ProyectoFullStack.Autenticacion.RepositoryAutenticacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ProyectoFullStack.Autenticacion.Entity.RolEntity;
@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {

    Optional<RolEntity> findByName(String name);

}
