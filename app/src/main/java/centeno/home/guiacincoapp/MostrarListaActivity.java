package centeno.home.guiacincoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Importa la clase Log
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import centeno.home.guiacincoapp.Entidades.Personas;
import centeno.home.guiacincoapp.ViewModel.PersonaViewModel;

public class MostrarListaActivity extends AppCompatActivity {

    private PersonaViewModel personaViewModel;
    private PersonaAdapter personaAdapter;
    private static final int REQUEST_CODE_EDITAR = 1; // Código de solicitud para la edición

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar_lista);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Verifica si el RecyclerView es nulo
        if (recyclerView == null) {
            Log.e("MostrarListaActivity", "El RecyclerView es nulo");
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            personaAdapter = new PersonaAdapter();
            recyclerView.setAdapter(personaAdapter);

            personaViewModel = new ViewModelProvider(this).get(PersonaViewModel.class);
            personaViewModel.getListaDePersonas().observe(this, new Observer<List<Personas>>() {
                @Override
                public void onChanged(List<Personas> personas) {
                    personaAdapter.setPersonasList(personas);
                }
            });

            // Configurar el OnItemClickListener para editar la persona
            personaAdapter.setOnItemClickListener(position -> {
                Personas personaSeleccionada = personaAdapter.getPersonaAt(position);
                mostrarDialogoConfirmacionEdicion(personaSeleccionada);
            });

            // Configurar el OnItemLongClickListener para eliminar la persona
            personaAdapter.setOnItemLongClickListener(position -> {
                Personas personaSeleccionada = personaAdapter.getPersonaAt(position);
                mostrarDialogoConfirmacionEliminacion(personaSeleccionada);
            });
        }
    }

    // Diálogo de confirmación para editar una persona
    private void mostrarDialogoConfirmacionEdicion(Personas persona) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres modificar esta persona?")
                .setTitle("Confirmación")
                .setPositiveButton("Sí", (dialog, id) -> {
                    // Abrir la actividad EditarPersonaActivity con los datos de la persona seleccionada
                    Intent intent = new Intent(MostrarListaActivity.this, EditarPersonaActivity.class);
                    intent.putExtra("idPersona", persona.idPersona);
                    intent.putExtra("nombre", persona.nombrePersona);
                    intent.putExtra("apellido", persona.apellidoPersona);
                    intent.putExtra("edad", persona.edadPersona);
                    editarPersonaLauncher.launch(intent); // Cambiado a usar el launcher
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Diálogo de confirmación para eliminar una persona
    private void mostrarDialogoConfirmacionEliminacion(Personas persona) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres eliminar esta persona?")
                .setTitle("Confirmación")
                .setPositiveButton("Sí", (dialog, id) -> {
                    // Eliminar la persona usando el ViewModel
                    personaViewModel.eliminarPersona(persona); // Usa el método en tu ViewModel

                    // Mostrar un mensaje de éxito
                    Toast.makeText(this, "Persona eliminada correctamente", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Maneja el resultado de la edición de la persona
    private final ActivityResultLauncher<Intent> editarPersonaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    int id = data.getIntExtra("idPersona", -1);
                    String nombre = data.getStringExtra("nombre");
                    String apellido = data.getStringExtra("apellido");
                    int edad = data.getIntExtra("edad", -1);

                    if (id != -1) {
                        Personas personaActualizada = new Personas(id, nombre, apellido, edad);
                        personaViewModel.actualizarPersona(personaActualizada);
                    }
                }
            }
    ); // Eliminado el punto y coma incorrecto
}
