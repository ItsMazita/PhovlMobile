package com.phovl.puntodeventaphovl;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phovl.puntodeventaphovl.adapters.LocalAdapter;
import com.phovl.puntodeventaphovl.room.LocalEntity;
import com.phovl.puntodeventaphovl.room.LocalViewModel;

public class LocalesActivity extends AppCompatActivity {

    private RecyclerView recyclerLocales;
    private LocalAdapter adapter;
    private LocalViewModel localViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_locales);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerLocales = findViewById(R.id.recyclerLocales);

        // Grid 2 columnas
        recyclerLocales.setLayoutManager(new GridLayoutManager(this, 2));

        // Spacing
        int spacing = 6;
        recyclerLocales.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = spacing;
                outRect.right = spacing;
                outRect.top = spacing;
                outRect.bottom = spacing;
            }
        });

        // Inicializar adapter vacío
        adapter = new LocalAdapter(this, local -> {
            Intent intent = new Intent(LocalesActivity.this, AdminLocalActivity.class);
            intent.putExtra("nombreLocal", local.getNombre());
            intent.putExtra("imagenLocal", 0); // Para despues
            intent.putExtra("descripcionLocal", local.getDescripcion());
            startActivity(intent);
        });

        recyclerLocales.setAdapter(adapter);

        // Conectamos el ViewModel
        localViewModel = new ViewModelProvider(this).get(LocalViewModel.class);

        // Observamos cambios en los datos del Room
        localViewModel.getAllLocales().observe(this, locales -> {
            if (locales == null || locales.isEmpty()) {
                insertarLocalesPorDefecto();
            } else {
                adapter.setLocales(locales);
            }
        });
    }

    // Insertamos MockData en el room
    private void insertarLocalesPorDefecto() {
        LocalEntity local1 = new LocalEntity(
                "Local Centro",
                "",
                "Av. Principal",
                "Tienda dedicada a electronicos"
        );
        LocalEntity local2 = new LocalEntity(
                "Local Barrancos",
                "",
                "Calle Rodolfo",
                "Tienda dedicada a pan"
        );
        LocalEntity local3 = new LocalEntity(
                "Local CU",
                "",
                "Av. Cultural",
                "Tienda dedicada a frutas :D"
        );

        localViewModel.insert(local1);
        localViewModel.insert(local2);
        localViewModel.insert(local3);
    }
}
