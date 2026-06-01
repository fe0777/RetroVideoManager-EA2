package proyecto.MS_Inventario_Fisico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.MS_Inventario_Fisico.model.Cinta;

@Repository
public interface CintaRepository extends JpaRepository<Cinta, Long> {

}