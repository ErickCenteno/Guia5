package centeno.home.guiacincoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import centeno.home.guiacincoapp.Entidades.Personas;
import centeno.home.guiacincoapp.ViewModel.PersonaViewModel;


public class AgregarPersonaActivity extends AppCompatActivity {
    private EditText etNombre, etApellido, etEdad;
    private PersonaViewModel personaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_persona);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        personaViewModel = new ViewModelProvider(this).get(PersonaViewModel.class);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String edadString = etEdad.getText().toString();

                // Validar que todos los campos estén llenos
                if (nombre.isEmpty() || apellido.isEmpty() || edadString.isEmpty()) {
                    // Mostrar mensaje de advertencia si falta algún campo
                    Toast.makeText(v.getContext(), "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                    return; // Salir del método si hay campos vacíos
                }

                int edad = Integer.parseInt(edadString);

                Personas persona = new Personas();
                persona.nombrePersona = nombre;
                persona.apellidoPersona = apellido;
                persona.edadPersona = edad;

                personaViewModel.insertPersona(persona);

                // Mostrar un mensaje de éxito
                Toast.makeText(v.getContext(), "Persona agregada correctamente", Toast.LENGTH_SHORT).show();

                finish(); // Cierra la actividad después de guardar.
            }
        });

    }
}
