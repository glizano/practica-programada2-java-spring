package cr.ac.una.services;

import cr.ac.una.entities.Persona;
import cr.ac.una.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Persona createPersona(Persona persona){
        return personaRepository.save(persona);
    }

    public Persona update(Persona persona){
        return personaRepository.save(persona);
    }

    public boolean deletePersona(Long id){
        try {
            personaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Optional<Persona> findPersonaById(Long id){
        return personaRepository.findById(id);
    }

    public List<Persona> findAll(){
        List<Persona> result = new ArrayList<Persona>();
        personaRepository.findAll().forEach(e -> result.add(e));
        return result;
    }


}
