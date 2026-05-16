package PROYECTOSEMESTAL.MS_Alquileres.repository;

import org.springframework.stereotype.Repository;

import PROYECTOSEMESTAL.MS_Alquileres.Modelo.cinta;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface repository_cinta extends JpaRepository<cinta, Long> {

}
