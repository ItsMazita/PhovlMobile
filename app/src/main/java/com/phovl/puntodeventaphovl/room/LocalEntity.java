package com.phovl.puntodeventaphovl.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "locales")
public class LocalEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private String imagen; // ruta local o base64
    private String direccion;
    private String descripcion;

    // Constructor
    public LocalEntity(String nombre, String imagen, String direccion, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.direccion = direccion;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
