package com.phovl.puntodeventaphovl;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        //Obtenemos los datos enviados desde LocalesActivity
        String nombreLocal = getIntent().getStringExtra("nombreLocal");
        int imagenLocal = getIntent().getIntExtra("imagenLocal", 0);

        //Buscamos los elementos en el layout
        TextView txtNombre = findViewById(R.id.txtNombreLocalAdmin);
        ImageView imgLocal = findViewById(R.id.imgLocalAdmin);

        //Colocamos los datos
        txtNombre.setText(nombreLocal);
        if (imagenLocal != 0) {
            imgLocal.setImageResource(imagenLocal);
        }
    }
}
