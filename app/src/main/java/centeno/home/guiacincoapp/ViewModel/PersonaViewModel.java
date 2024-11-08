package centeno.home.guiacincoapp.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import centeno.home.guiacincoapp.DAO.PersonaDAO;
import centeno.home.guiacincoapp.Database.PersonasDatabase;
import centeno.home.guiacincoapp.Entidades.Personas;

public class PersonaViewModel extends AndroidViewModel {

    private PersonaDAO personaDAO;
    private LiveData<List<Personas>> listaDePersonas;

    public PersonaViewModel(Application application) {
        super(application);
        PersonasDatabase database = PersonasDatabase.getInstance(application);
        personaDAO = database.personaDAO();
        listaDePersonas = personaDAO.obtenerTodasLasPersonas();
    }

    // Método para obtener la lista observable de todas las personas
    public LiveData<List<Personas>> getListaDePersonas() {
        return listaDePersonas;
    }

    // Método para insertar una persona en la base de datos, ejecutado en un hilo secundario
    public void insertPersona(Personas persona) {
        new Thread(() -> personaDAO.insert(persona)).start();
    }

    // Método para eliminar una persona de la base de datos, ejecutado en un hilo secundario
    public void eliminarPersona(Personas persona) {
        new Thread(() -> personaDAO.delete(persona)).start();
    }

    // Método para actualizar una persona en la base de datos, ejecutado en un hilo secundario
    public void actualizarPersona(Personas persona) {
        new Thread(() -> personaDAO.update(persona)).start();
    }
}

