package com.example.drugcardapp.ui.report;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drugcardapp.database.AppRepository;
import com.example.drugcardapp.database.DrugCardEntity;

import java.util.List;

public class ReportViewModel extends AndroidViewModel {

    public LiveData<List<DrugCardEntity>> mcards;

    private AppRepository mRepository;

    public ReportViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mcards = mRepository.mcards;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public LiveData<List<DrugCardEntity>> getCardsBySystem(String systemStr) {
        return mRepository.getCardsBySystem(systemStr);
    }
}