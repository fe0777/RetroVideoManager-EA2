package com.RetroVideoManager.Peliculas.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RetroVideoManager.Peliculas.Model.peliculasModel;

@Repository
public interface peliculasRepository extends JpaRepository<peliculasModel, Long> {

}
