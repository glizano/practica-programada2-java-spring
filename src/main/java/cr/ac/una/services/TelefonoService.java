package cr.ac.una.services;

import cr.ac.una.entities.Telefono;
import cr.ac.una.repositories.TelefonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelefonoService {

    @Autowired
    private TelefonoRepository telefonoRepository;

    public Telefono createTelefono(Telefono telefono){
        return telefonoRepository.save(telefono);
    }

    public Telefono update(Telefono telefono){
        return telefonoRepository.save(telefono);
    }

    public void deleteTelefono(Long id){
        telefonoRepository.deleteById(id);
    }

    public Optional<Telefono> findTelefonoById(Long id){
        return telefonoRepository.findById(id);
    }

    public List<Telefono> findAll(){
        List<Telefono> result = new ArrayList<Telefono>();
        telefonoRepository.findAll().forEach(e -> result.add(e));
        return result;
    }


}
