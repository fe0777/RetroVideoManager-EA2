package proyecto.MS_Inventario_Fisico.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventario")
@Data

public class Cinta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idPelicula;

    @Column(nullable = false)
    private String disponible;

    @Column(nullable = false)
    private String reservada;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private LocalDate fechaIngreso;
}