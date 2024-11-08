package centeno.home.guiacincoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    private Button btnAgregarPersona;
    private Button btnMostrarLista;
    private Button btnAcerca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregarPersona = findViewById(R.id.btnAgregarPersona);
        btnMostrarLista = findViewById(R.id.btnMostrarLista);
        btnAcerca = findViewById(R.id.btnAcerca);

        btnAgregarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, AgregarPersonaActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("AgregarPersonaActivity", "Error al guardar persona: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        });

        btnMostrarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MostrarListaActivity.class);
                startActivity(intent);
            }
        });

        btnAcerca.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // Crear un Intent para ir a la otra actividad
                Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
                startActivity(intent);
            }
        });
    }
}
