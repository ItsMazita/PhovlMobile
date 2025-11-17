package com.phovl.puntodeventaphovl;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Rect;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phovl.puntodeventaphovl.adapters.LocalAdapter;
import com.phovl.puntodeventaphovl.models.Local;

import java.util.ArrayList;
import java.util.List;

public class LocalesActivity extends AppCompatActivity {

    private RecyclerView recyclerLocales;
    private List<Local> listaLocales;

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

        // ⭐ GridLayoutManager centrado
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recyclerLocales.setLayoutManager(grid);

        // ⭐ Espaciado entre items y separación de los bordes
        int spacing = 24; // dp
        recyclerLocales.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = spacing;
                outRect.right = spacing;
                outRect.top = spacing;
                outRect.bottom = spacing;
            }
        });

        // ⭐ Lista de locales
        listaLocales = new ArrayList<>();
        listaLocales.add(new Local("Sucursal 1", R.drawable.local1));
        listaLocales.add(new Local("Sucursal 2", R.drawable.local1));
        listaLocales.add(new Local("Sucursal 3", R.drawable.local1));
        listaLocales.add(new Local("Sucursal 4", R.drawable.local1));
        listaLocales.add(new Local("Sucursal 5", R.drawable.local1));

        // ⭐ Adapter con listener
        LocalAdapter adapter = new LocalAdapter(this, listaLocales, local -> {
            Intent intent = new Intent(LocalesActivity.this, AdminLocalActivity.class);
            intent.putExtra("nombreLocal", local.getNombre());
            intent.putExtra("imagenLocal", local.getImagen());
            startActivity(intent);
        });

        recyclerLocales.setAdapter(adapter);
    }
}
