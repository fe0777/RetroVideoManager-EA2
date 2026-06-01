package com.RetroVideoManager.Peliculas.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Entity
    @Table(name = "peliculas")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
public class peliculasModel {
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private Integer anio;

    @Column(nullable = false)
    private String genero;

    @Column(length = 500, nullable = false)
    private String sinopsis;
}
