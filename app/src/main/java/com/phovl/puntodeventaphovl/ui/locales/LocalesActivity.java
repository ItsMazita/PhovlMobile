package com.phovl.puntodeventaphovl.ui.locales;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phovl.puntodeventaphovl.R;
import com.phovl.puntodeventaphovl.data.room.LocalEntity;
import com.phovl.puntodeventaphovl.ui.locales.admin.AdminLocalActivity;

public class LocalesActivity extends AppCompatActivity {

    private RecyclerView recyclerLocales;
    private LocalAdapter adapter;
    private LocalViewModel localViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_locales);

        // Ajuste de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });

        recyclerLocales = findViewById(R.id.recyclerLocales);

        // Grid con 2 columnas
        recyclerLocales.setLayoutManager(new GridLayoutManager(this, 2));

        // Espaciado entre ítems
        int spacing = 16;
        recyclerLocales.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(spacing, spacing, spacing, spacing);
            }
        });

        // Inicializar adapter
        adapter = new LocalAdapter(this, local -> {
            Intent intent = new Intent(LocalesActivity.this, AdminLocalActivity.class);
            intent.putExtra("nombreLocal", local.getNombre());
            intent.putExtra("imagenLocal", 0); // Para después
            intent.putExtra("descripcionLocal", local.getDescripcion());
            startActivity(intent);
        });
        recyclerLocales.setAdapter(adapter);

        // Conectar ViewModel
        localViewModel = new ViewModelProvider(this).get(LocalViewModel.class);

        // Observar cambios en Room
        localViewModel.getAllLocales().observe(this, locales -> {
            if (locales == null || locales.isEmpty()) {
                insertarLocalesPorDefecto();
            } else {
                adapter.setLocales(locales);
            }
        });
    }

    // Insertar datos de prueba
    private void insertarLocalesPorDefecto() {
        localViewModel.insert(new LocalEntity("Local Centro", "", "Av. Principal", "Tienda dedicada a electrónicos"));
        localViewModel.insert(new LocalEntity("Local Barrancos", "", "Calle Rodolfo", "Tienda dedicada a pan"));
        localViewModel.insert(new LocalEntity("Local CU", "", "Av. Cultural", "Tienda dedicada a frutas :D"));
        localViewModel.insert(new LocalEntity("Local Mercado", "", "Av. Mercado", "Tienda dedicada a bicicletas"));
        localViewModel.insert(new LocalEntity("Local Alturas", "", "Av. Ley Fong", "Tienda dedicada a zapatos"));
    }
}
