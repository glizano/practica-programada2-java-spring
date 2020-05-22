package cr.ac.una.api;

import cr.ac.una.entities.Persona;
import cr.ac.una.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaRest {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll(){
        return ResponseEntity.ok(personaService.findAll());
    }

    @PostMapping
    public ResponseEntity<Persona> create(@Valid @RequestBody Persona persona){
        return ResponseEntity.ok(personaService.createPersona(persona));
    }

    @PutMapping
    public ResponseEntity<Persona> update(@Valid @RequestBody Persona persona){
        if(!personaService.findPersonaById(persona.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personaService.update(persona));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        if(!personaService.findPersonaById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        personaService.deletePersona(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Persona> findById(@PathVariable Long id){
        if(!personaService.findPersonaById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personaService.findPersonaById(id).get());
    }
}
