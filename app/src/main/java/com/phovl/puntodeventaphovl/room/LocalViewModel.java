package com.phovl.puntodeventaphovl.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocalViewModel extends AndroidViewModel {

    private final LocalRepository repository;
    private final LiveData<List<LocalEntity>> listaLocales;

    public LocalViewModel(@NonNull Application application) {
        super(application);

        repository = new LocalRepository(application);
        listaLocales = repository.getAll();   // Obtiene LiveData
    }

    public LiveData<List<LocalEntity>> getAllLocales() {
        return listaLocales;
    }

    public void insert(LocalEntity local) {
        repository.insert(local);
    }

    public void update(LocalEntity local) {
        repository.update(local);
    }

    public void delete(LocalEntity local) {
        repository.delete(local);
    }
}
