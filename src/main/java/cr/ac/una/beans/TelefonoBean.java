package cr.ac.una.beans;

import cr.ac.una.entities.Direccion;
import cr.ac.una.entities.Persona;
import cr.ac.una.entities.Telefono;
import cr.ac.una.services.PersonaService;
import cr.ac.una.services.TelefonoService;
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
public class TelefonoBean {

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private PersonaService personaService;

    private List<Telefono> telefonos;
    private List<Telefono> telefonosFiltrados;
    private Telefono telefono = new Telefono();

    private List<Persona> personas;


    @PostConstruct
    public void init(){
        telefonos = telefonoService.findAll();
        personas = personaService.findAll();
        telefonosFiltrados = new ArrayList(telefonos);
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public void delete(){
        Long id = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("telefono_id"));
        telefonoService.deleteTelefono(id);
        addMessage("aviso","telefono borrado correctamente");
        telefonos = telefonoService.findAll();
        telefonosFiltrados = new ArrayList(telefonos);
        clearAllFilters();
    }

    public void create(){
        //telefono.setPersona(personaService.findPersonaById(telefono.getPersona().getId()).get());
        telefonoService.createTelefono(telefono);
        addMessage("aviso","telefono creado correctamente");
        telefonos = telefonoService.findAll();
        telefonosFiltrados = new ArrayList(telefonos);
        telefono = new Telefono();
    }

    public void addMessage(String resumen, String detalle){
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, resumen, detalle);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }

    public void update(){
        //telefono.setPersona(personaService.findPersonaById(telefono.getPersona().getId()).get());
        telefonoService.createTelefono(telefono);
        addMessage("aviso","telefono actualizado correctamente");
        telefonos = telefonoService.findAll();
        telefonosFiltrados = new ArrayList(telefonos);
    }

    public String carga(Telefono telefono) throws IOException {
        this.telefono = telefono;
        return "telefonoUpdate?faces-redirect=true";
    }

    public String carga() throws IOException {
        personas = personaService.findAll();
        this.telefono = new Telefono();
        return "telefonoCreate?faces-redirect=true";
    }

    public String cargaList(Long id) throws IOException {
        telefonos = telefonoService.findAll();
        if(id != null){
            telefonos = telefonos.stream().filter(telefono1 -> telefono1.getPersona().getId() == id).collect(Collectors.toList());
        }
        personas = personaService.findAll();
        telefonosFiltrados = new ArrayList(telefonos);
        PrimeFaces.current().ajax().update("form:tabla-telefono");
        return "telefonoList?faces-redirect=true";
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Telefono> getTelefonosFiltrados() {
        return telefonosFiltrados;
    }

    public void setTelefonosFiltrados(List<Telefono> telefonosFiltrados) {
        this.telefonosFiltrados = telefonosFiltrados;
    }

    public void clearAllFilters() {

        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:tabla-telefono");
        if (!dataTable.getFilters().isEmpty()) {
            dataTable.reset();
        }
        PrimeFaces.current().ajax().update("form:tabla-telefono");
    }
}
