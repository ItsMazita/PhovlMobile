package com.phovl.puntodeventaphovl.data.room.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.phovl.puntodeventaphovl.data.room.AppDatabase;
import com.phovl.puntodeventaphovl.data.room.LocalDao;
import com.phovl.puntodeventaphovl.data.room.LocalEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

public class LocalRepository {

    private final LocalDao localDao;
    private final ExecutorService executorService;

    public LocalRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        localDao = db.localDao();

        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(LocalEntity local) {
        executorService.execute(() -> localDao.insertarLocal(local));
    }

    public void update(LocalEntity local) {
        executorService.execute(() -> localDao.actualizarLocal(local));
    }

    public void delete(LocalEntity local) {
        executorService.execute(() -> localDao.eliminarLocal(local));
    }

    public LiveData<List<LocalEntity>> getAll() {
        return localDao.obtenerTodosLosLocales();
    }
}
