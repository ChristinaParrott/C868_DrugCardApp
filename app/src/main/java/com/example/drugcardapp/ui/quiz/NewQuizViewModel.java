package com.example.drugcardapp.ui.quiz;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.drugcardapp.database.AppRepository;
import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.database.QuizEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewQuizViewModel extends AndroidViewModel {
    public MutableLiveData<QuizEntity> mquiz = new MutableLiveData<>();
    public LiveData<List<QuizEntity>> mquizzes;
    public LiveData<List<DrugCardEntity>> mcards;

    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public NewQuizViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mquizzes = mRepository.mquizzes;
        mcards = mRepository.mcards;
    }
    public void loadData(int quizId) {
        executor.execute(() -> {
            QuizEntity quiz = mRepository.getQuizById(quizId);
            mquiz.postValue(quiz);
        });
    }

    public void addQuiz(String quizName, ArrayList<DrugCardEntity> addCards) {
        QuizEntity quiz = mquiz.getValue();
        if(quiz == null) {
            if (TextUtils.isEmpty(quizName)){
                return;
            }
            else{
                quiz = new QuizEntity(quizName, addCards);
            }
        }
        else {
            quiz.setQuizName(quizName);
            quiz.setQuizCards(addCards);
        }
        mRepository.insertQuiz(quiz);
    }
    public void deleteQuiz() {
        mRepository.deleteQuiz(mquiz.getValue());
    }
}