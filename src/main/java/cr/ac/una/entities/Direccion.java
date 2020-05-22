package cr.ac.una.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="direccion")
public class Direccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String otras_senas;

    private Long codigo_postal;

    @ManyToOne
    @JoinColumn(name = "persona")
    private Persona persona;

    public Direccion() {
        persona = new Persona();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtras_senas() {
        return otras_senas;
    }

    public void setOtras_senas(String otras_senas) {
        this.otras_senas = otras_senas;
    }

    public Long getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(Long codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
