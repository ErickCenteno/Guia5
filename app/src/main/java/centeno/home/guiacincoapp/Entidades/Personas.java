package centeno.home.guiacincoapp.Entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Definir la entidad Personas para Room Database
@Entity
public class Personas implements Serializable {

    // Clave primaria que se autogenera
    @PrimaryKey(autoGenerate = true)
    public int idPersona;

    // Campos de la entidad
    public String nombrePersona;
    public String apellidoPersona;
    public int edadPersona;

    // Constructor vacío requerido por Room
    public Personas() {
    }

    // Constructor para crear una nueva persona (usado para la inserción)
    public Personas(String nombrePersona, String apellidoPersona, int edadPersona) {
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.edadPersona = edadPersona;
    }

    // Constructor para actualizar una persona (con ID)
    public Personas(int idPersona, String nombrePersona, String apellidoPersona, int edadPersona) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.edadPersona = edadPersona;
    }

    // Sobreescribir toString para mostrar una representación legible de la entidad
    @NonNull
    @Override
    public String toString() {
        return nombrePersona + " " + apellidoPersona + " " + edadPersona;
    }
}

