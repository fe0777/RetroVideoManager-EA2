package com.membresias.membresias.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "membresias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, length = 100)
    private String nombreUsuario;

    @Column(name = "tipo_membresia", nullable = false, length = 50)
    private String tipoMembresia;

    @Column(nullable = false)
    private Double precio;

    @Column(name = "duracion_dias", nullable = false)
    private Integer duracionDias;

    @Column(length = 500)
    private String beneficios;
}









  

 

    
