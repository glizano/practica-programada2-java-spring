package cr.ac.una.repositories;

import cr.ac.una.entities.Telefono;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepository extends CrudRepository<Telefono, Long> {


}
