package com.phovl.puntodeventaphovl.models;

public class Local {

    private String nombre;
    private int imagen;

    public Local(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }
}
