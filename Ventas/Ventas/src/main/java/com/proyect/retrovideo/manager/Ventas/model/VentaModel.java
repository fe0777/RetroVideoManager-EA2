package com.proyect.retrovideo.manager.Ventas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Long peliculaId;

    @Column(nullable = false)
    private String tituloPelicula;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private LocalDateTime fechaVenta;

    @Column(nullable = false)
    private String estado;
}
