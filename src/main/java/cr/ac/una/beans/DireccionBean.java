package cr.ac.una.beans;

import cr.ac.una.entities.Direccion;
import cr.ac.una.entities.Persona;
import cr.ac.una.entities.Direccion;
import cr.ac.una.services.PersonaService;
import cr.ac.una.services.DireccionService;
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
import java.util.stream.Collectors;

@Component
public class DireccionBean {

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private PersonaService personaService;

    private List<Direccion> direccions;
    private List<Direccion> direccionsFiltrados;
    private Direccion direccion = new Direccion();

    private List<Persona> personas;


    @PostConstruct
    public void init(){
        direccions = direccionService.findAll();
        personas = personaService.findAll();
        direccionsFiltrados = new ArrayList(direccions);
    }

    public List<Direccion> getDireccions() {
        return direccions;
    }

    public void setDireccions(List<Direccion> direccions) {
        this.direccions = direccions;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void delete(){
        Long id = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("direccion_id"));
        direccionService.deleteDireccion(id);
        addMessage("aviso","direccion borrado correctamente");
        direccions = direccionService.findAll();
        direccionsFiltrados = new ArrayList(direccions);
        clearAllFilters();
    }

    public void create(){
        //direccion.setPersona(personaService.findPersonaById(direccion.getPersona().getId()).get());
        direccionService.createDireccion(direccion);
        addMessage("aviso","direccion creado correctamente");
        direccions = direccionService.findAll();
        direccionsFiltrados = new ArrayList(direccions);
        direccion = new Direccion();
    }

    public void addMessage(String resumen, String detalle){
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, resumen, detalle);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }

    public void update(){
        //direccion.setPersona(personaService.findPersonaById(direccion.getPersona().getId()).get());
        direccionService.createDireccion(direccion);
        addMessage("aviso","direccion actualizado correctamente");
        direccions = direccionService.findAll();
        direccionsFiltrados = new ArrayList(direccions);
    }

    public String carga(Direccion direccion) throws IOException {
        this.direccion = direccion;
        return "direccionUpdate?faces-redirect=true";
    }

    public String carga() throws IOException {
        personas = personaService.findAll();
        this.direccion = new Direccion();
        return "direccionCreate?faces-redirect=true";
    }

    public String cargaList(Long id) throws IOException {
        direccions = direccionService.findAll();
        if(id != null){
            direccions = direccions.stream().filter(direccion1 -> direccion1.getPersona().getId() == id).collect(Collectors.toList());
        }
        personas = personaService.findAll();
        direccionsFiltrados = new ArrayList(direccions);
        PrimeFaces.current().ajax().update("form:tabla-direccion");
        return "direccionList?faces-redirect=true";
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Direccion> getDireccionsFiltrados() {
        return direccionsFiltrados;
    }

    public void setDireccionsFiltrados(List<Direccion> direccionsFiltrados) {
        this.direccionsFiltrados = direccionsFiltrados;
    }

    public void clearAllFilters() {

        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:tabla-direccion");
        if (!dataTable.getFilters().isEmpty()) {
            dataTable.reset();
        }
        PrimeFaces.current().ajax().update("form:tabla-direccion");
    }
}
