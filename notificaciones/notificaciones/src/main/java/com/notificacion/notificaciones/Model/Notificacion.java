package com.notificacion.notificaciones.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destinatario;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Boolean leida;
}
