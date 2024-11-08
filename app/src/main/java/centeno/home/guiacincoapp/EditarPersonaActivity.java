package centeno.home.guiacincoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import centeno.home.guiacincoapp.Entidades.Personas;
import centeno.home.guiacincoapp.ViewModel.PersonaViewModel;


public class EditarPersonaActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etApellido;
    private EditText etEdad;
    private Button btnGuardar;
    private PersonaViewModel personaViewModel; // ViewModel para manejar los datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_persona); // Asegúrate de que este es tu layout correcto

        // Inicializar los EditText y el botón
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Inicializar el ViewModel
        personaViewModel = new ViewModelProvider(this).get(PersonaViewModel.class);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        int idPersona = intent.getIntExtra("idPersona", -1);
        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        int edad = intent.getIntExtra("edad", -1);

        // Asignar los datos a los EditText
        etNombre.setText(nombre);
        etApellido.setText(apellido);
        etEdad.setText(String.valueOf(edad));

        // Configurar el botón Guardar
        btnGuardar.setOnClickListener(view -> {
            // Validar que todos los campos estén llenos
            if (etNombre.getText().toString().isEmpty() || etApellido.getText().toString().isEmpty() || etEdad.getText().toString().isEmpty()) {
                // Mostrar mensaje de advertencia si falta algún campo
                Toast.makeText(view.getContext(), "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                return; // Salir del método si hay campos vacíos
            }

            // Crear un objeto Persona con los datos actuales
            Personas persona = new Personas(idPersona, etNombre.getText().toString(), etApellido.getText().toString(), Integer.parseInt(etEdad.getText().toString()));

            // Actualizar la persona usando el ViewModel
            personaViewModel.actualizarPersona(persona);

            // Mostrar un mensaje de éxito
            Toast.makeText(this, "Persona actualizada correctamente", Toast.LENGTH_SHORT).show();

            // Regresar a la actividad anterior
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish(); // Esto cierra la actividad actual y regresa a la anterior
        });
    }
}
