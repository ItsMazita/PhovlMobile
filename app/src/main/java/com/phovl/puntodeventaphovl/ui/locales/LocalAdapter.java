package com.phovl.puntodeventaphovl.ui.locales;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phovl.puntodeventaphovl.R;
import com.phovl.puntodeventaphovl.data.room.LocalEntity;

import java.util.ArrayList;
import java.util.List;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder> {

    private List<LocalEntity> listaLocales = new ArrayList<>(); // ← ahora LocalEntity
    private final Context context;
    private final OnItemClickListener listener;

    @SuppressLint("NotifyDataSetChanged")
    public void setLocales(List<LocalEntity> locales) {
        this.listaLocales = locales;
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(LocalEntity local);
    }

    public LocalAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_local, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocalEntity local = listaLocales.get(position);

        holder.nombre.setText(local.getNombre());

        // Si algún día guardas imágenes, aquí se pondrá tu lógica
        holder.imagen.setImageResource(R.drawable.ic_launcher_foreground);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(local);
        });
    }

    @Override
    public int getItemCount() {
        return listaLocales.size();
    }

    public void updateData(List<LocalEntity> nuevosLocales) {
        listaLocales = nuevosLocales;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtNombreLocal);
            imagen = itemView.findViewById(R.id.imgLocal);
        }
    }
}
