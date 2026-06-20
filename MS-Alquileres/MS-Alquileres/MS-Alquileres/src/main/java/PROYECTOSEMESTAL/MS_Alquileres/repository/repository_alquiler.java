package PROYECTOSEMESTAL.MS_Alquileres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PROYECTOSEMESTAL.MS_Alquileres.Modelo.alquiler;

@Repository
public interface repository_alquiler extends JpaRepository<alquiler, Long>{
    
}
