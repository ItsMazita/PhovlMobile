package com.phovl.puntodeventaphovl.ui.locales.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.phovl.puntodeventaphovl.R;


public class AdminLocalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_local);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Boton para volver a la seleccion de locales
        ImageButton btnRegresar = findViewById(R.id.btn_regresar);
        btnRegresar.setOnClickListener(v -> {
            finish(); // Cierra la pantalla actual y regresa a la anterior
        });


        //Boton para abrir la pantalla de productos
        ConstraintLayout btnProductos = findViewById(R.id.btn_productos_local);
        btnProductos.setOnClickListener(v -> {
            Intent intent = new Intent(AdminLocalActivity.this, ProductosLocalActivity.class);
            startActivity(intent);
        });

        //Boton para abrir la pantalla de estadisticas
        ConstraintLayout btnEstadisticas = findViewById(R.id.btn_estadisticas_local);
        btnEstadisticas.setOnClickListener(v -> {
            Intent intent = new Intent(AdminLocalActivity.this, EstadisticasLocalActivity.class);
            startActivity(intent);
        });

        //Boton para abrir la pantalla de configuracion
        ConstraintLayout btnConfiguracion = findViewById(R.id.btn_configuracion_local);
        btnConfiguracion.setOnClickListener(v -> {
            Intent intent = new Intent(AdminLocalActivity.this, ConfiguracionLocalActivity.class);
            startActivity(intent);
        });



        //Obtenemos los datos enviados desde LocalesActivity
        String nombreLocal = getIntent().getStringExtra("nombreLocal");
        String descripcion = getIntent().getStringExtra("descripcionLocal");

        //Buscamos los elementos en el layout
        TextView txtNombre = findViewById(R.id.txtNombreLocalAdmin);
        TextView txtDescripcion = findViewById(R.id.txtDescripcionAdmin);

        //Colocamos los datos
        txtNombre.setText(nombreLocal);
        txtDescripcion.setText(descripcion);
    }
}
