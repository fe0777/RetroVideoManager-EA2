package PROYECTOSEMESTAL.MS_Alquileres.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Cinta")
@Data


public class cinta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cinta_id;
    
    private String nombre_cinta;
}
