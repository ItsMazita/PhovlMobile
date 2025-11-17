package com.phovl.puntodeventaphovl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phovl.puntodeventaphovl.R;
import com.phovl.puntodeventaphovl.models.Local;

import java.util.List;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder> {

    private List<Local> listaLocales;
    private Context context;
    private OnItemClickListener listener;

    // 🔹 Interfaz para manejar clicks en un local
    public interface OnItemClickListener {
        void onItemClick(Local local);
    }

    public LocalAdapter(Context context, List<Local> listaLocales, OnItemClickListener listener) {
        this.context = context;
        this.listaLocales = listaLocales;
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
        Local local = listaLocales.get(position);

        holder.nombre.setText(local.getNombre());
        holder.imagen.setImageResource(local.getImagen());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(local);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLocales.size();
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
