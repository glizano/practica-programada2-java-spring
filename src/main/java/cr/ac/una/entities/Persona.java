package cr.ac.una.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="persona")
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long identificacion;

    private String nombre;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/CostaRica")
    private Date fecha;

    @OneToMany(mappedBy = "persona")
    private Collection<Telefono> personaCollection;

    @OneToMany(mappedBy = "persona")
    private Collection<Direccion> direccionCollection;

    public Persona(){

    }

    public Persona(Long id, Long identificacion, String nombre, Date fecha) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Collection<Telefono> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Telefono> personaCollection) {
        this.personaCollection = personaCollection;
    }

    public Collection<Direccion> getDireccionCollection() {
        return direccionCollection;
    }

    public void setDireccionCollection(Collection<Direccion> direccionCollection) {
        this.direccionCollection = direccionCollection;
    }
}
