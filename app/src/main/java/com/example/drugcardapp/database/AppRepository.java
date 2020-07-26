package com.example.drugcardapp.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.drugcardapp.util.SampleCards;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    public LiveData<List<DrugCardEntity>> mcards;
    public LiveData<List<QuizEntity>> mquizzes;

    private AppDatabase mDB;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }
    private AppRepository(Context context) {
        mDB = AppDatabase.getInstance((context));
        mcards = getAllDrugCards();
        mquizzes = getAllQuizzes();
    }
    private LiveData<List<DrugCardEntity>> getAllDrugCards() {
        return mDB.drugCardDAO().getAllDrugCards();
    }
    private LiveData<List<QuizEntity>> getAllQuizzes() {
        return mDB.quizDAO().getAllQuizzes();
    }
    public void deleteAll(){
        executor.execute(() ->
                mDB.drugCardDAO().deleteAllDrugCards());
        executor.execute(() ->
                mDB.quizDAO().deleteAllQuizzes());
    }
    public void insertCard(final DrugCardEntity card) {
        executor.execute(() -> mDB.drugCardDAO().insertDrugCard(card));
    }
    public void deleteCard(final DrugCardEntity card){
        executor.execute(() -> mDB.drugCardDAO().deleteDrugCard(card));
    }

    public void addSampleData() {
        executor.execute(()-> {
            mDB.drugCardDAO().insertAllDrugCards(SampleCards.getCards());
            mDB.quizDAO().insertAllQuizzes(SampleCards.getQuizzes());
        });
    }
    public LiveData<List<DrugCardEntity>> getCardsBySystem(String systemStr) {
        return mDB.drugCardDAO().getCardsBySystem(systemStr);
    }

    public DrugCardEntity getCardById(int cardId) {
        return mDB.drugCardDAO().getCardByID(cardId);
    }

    public QuizEntity getQuizById(int quizId) {
        return mDB.quizDAO().getQuizByID(quizId);
    }

    public void insertQuiz(QuizEntity quiz) {
        executor.execute(()->{
            mDB.quizDAO().insertQuiz(quiz);
        });
    }

    public void deleteQuiz(QuizEntity quiz) {
        executor.execute(()->
        mDB.quizDAO().deleteQuiz(quiz)
        );
    }

}
