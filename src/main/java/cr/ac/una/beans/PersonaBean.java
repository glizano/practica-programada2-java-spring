package cr.ac.una.beans;

import cr.ac.una.entities.Persona;
import cr.ac.una.services.PersonaService;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonaBean {

    @Autowired
    private PersonaService personaService;

    private List<Persona> personas;
    private List<Persona> personasFiltradas;
    private Persona persona = new Persona();

    @PostConstruct
    public void init(){
        personas = personaService.findAll();
        personasFiltradas = new ArrayList(personas);
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void delete(){
        Long id = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("persona_id"));
        if(personaService.deletePersona(id)) {
            addMessage("aviso", "persona borrada correctamente", FacesMessage.SEVERITY_INFO);
        }else{
            addMessage("aviso","persona es referenciada", FacesMessage.SEVERITY_ERROR);
        }
        personas = personaService.findAll();
        personasFiltradas = new ArrayList(personas);
        clearAllFilters();
    }

    public void create(){
        boolean existe = personas.stream().anyMatch(persona1 -> persona1.getIdentificacion() == persona.getIdentificacion());
        if(!existe){
            personaService.createPersona(persona);
            addMessage("aviso","persona creada correctamente", FacesMessage.SEVERITY_INFO);
            personas = personaService.findAll();
            personasFiltradas = new ArrayList(personas);
            persona = new Persona();
        }else{
            addMessage("aviso","ya existe la identificacion", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void addMessage(String resumen, String detalle, FacesMessage.Severity severity){
        FacesMessage mensaje = new FacesMessage(severity, resumen, detalle);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }

    public void update(){
        boolean existe = personas.stream().anyMatch(persona1 -> persona1.getIdentificacion() == persona.getIdentificacion());
        if(!existe){
            personaService.createPersona(persona);
            addMessage("aviso","persona actualizada correctamente", FacesMessage.SEVERITY_INFO);
            personas = personaService.findAll();
            personasFiltradas = new ArrayList(personas);
        }else{
            addMessage("aviso","ya existe la identificacion", FacesMessage.SEVERITY_ERROR);
        }
    }

    public String carga(Persona persona) throws IOException {
        this.persona = persona;
        return "personaUpdate";
    }

    public List<Persona> getPersonasFiltradas() {
        return personasFiltradas;
    }

    public void setPersonasFiltradas(List<Persona> personasFiltradas) {
        this.personasFiltradas = personasFiltradas;
    }

    public void clearAllFilters() {

        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:tabla-persona");
        if (!dataTable.getFilters().isEmpty()) {
            dataTable.reset();
        }
        PrimeFaces.current().ajax().update("form:tabla-persona");
    }
}
