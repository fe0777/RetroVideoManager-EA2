package com.TiendaVHS_RetroVideoManager.Usuarios.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelUsuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String username;

    @Column(unique = true, length = 13, nullable = true)
    private String run;

    @Column(nullable = true)
    private String nombres;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String apellidos;

    @Column(nullable = true)
    private String telefonoFijo;

    @Column(nullable = true)
    private String direccion;

    @Column(nullable = true)
    private String telefonoMovil;
}
