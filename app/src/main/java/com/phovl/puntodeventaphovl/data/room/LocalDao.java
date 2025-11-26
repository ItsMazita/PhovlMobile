package com.phovl.puntodeventaphovl.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocalDao {

    @Insert
    long insertarLocal(LocalEntity local);

    @Query("SELECT * FROM locales")
    LiveData<List<LocalEntity>> obtenerTodosLosLocales();

    @Query("SELECT * FROM locales WHERE id = :id")
    LocalEntity obtenerLocalPorId(int id);

    @Update
    void actualizarLocal(LocalEntity local);

    @Delete
    void eliminarLocal(LocalEntity local);

    @Query("DELETE FROM locales")
    void eliminarTodosLosLocales();
}
