package com.example.drugcardapp.ui.quiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drugcardapp.database.AppRepository;
import com.example.drugcardapp.database.QuizEntity;

import java.util.List;

public class QuizViewModel extends AndroidViewModel {
    public LiveData<List<QuizEntity>> mquizzes;

    private AppRepository mRepository;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mquizzes = mRepository.mquizzes;
    }

    public void deleteQuiz(QuizEntity quiz) {
        mRepository.deleteQuiz(quiz);
    }
}