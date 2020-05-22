package cr.ac.una.services;

import cr.ac.una.entities.Direccion;
import cr.ac.una.repositories.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public Direccion createDireccion(Direccion direccion){
        return direccionRepository.save(direccion);
    }

    public Direccion update(Direccion direccion){
        return direccionRepository.save(direccion);
    }

    public void deleteDireccion(Long id){
        direccionRepository.deleteById(id);
    }

    public Optional<Direccion> findDireccionById(Long id){
        return direccionRepository.findById(id);
    }

    public List<Direccion> findAll(){
        List<Direccion> result = new ArrayList<Direccion>();
        direccionRepository.findAll().forEach(e -> result.add(e));
        return result;
    }


}
