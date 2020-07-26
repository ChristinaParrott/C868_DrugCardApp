package com.example.drugcardapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drugcardapp.database.AppRepository;
import com.example.drugcardapp.database.DrugCardEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public LiveData<List<DrugCardEntity>> cards;

    private AppRepository mRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        cards = mRepository.mcards;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllData() {
        mRepository.deleteAll();
    }
}
