package cr.ac.una.repositories;

import cr.ac.una.entities.Direccion;
import cr.ac.una.entities.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends CrudRepository<Direccion, Long> {


}
