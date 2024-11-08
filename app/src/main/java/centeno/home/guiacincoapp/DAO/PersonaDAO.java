package centeno.home.guiacincoapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import centeno.home.guiacincoapp.Entidades.Personas;

@Dao
public interface PersonaDAO {

    @Insert
    void insert(Personas personaEntity);  // Método para insertar personas

    @Update
    void update(Personas personaEntity);  // Método para actualizar personas

    @Delete
    void delete(Personas personaEntity);  // Método para eliminar personas

    @Query("SELECT * FROM personas")
    LiveData<List<Personas>> obtenerTodasLasPersonas();  // Método para obtener todas las personas
}
