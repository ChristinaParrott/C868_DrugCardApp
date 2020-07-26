package com.example.drugcardapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.database.QuizEntity;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<DrugCardEntity> selected = new MutableLiveData<DrugCardEntity>();
    private final MutableLiveData<QuizEntity> selectedQuiz = new MutableLiveData<QuizEntity>();
    private final ArrayList<CharSequence> selectedChips = new ArrayList<>();
    private int index = 0;

    public void select(DrugCardEntity card) {
            selected.setValue(card);
    }

    public LiveData<DrugCardEntity> getSelected() {
        return selected;
    }

    public void selectQuiz(QuizEntity quiz) { selectedQuiz.setValue(quiz); }

    public LiveData<QuizEntity> getSelectedQuiz() {
        return selectedQuiz;
    }

    public void selectChips(ArrayList<CharSequence> chips) {
        if(chips != null) {
            selectedChips.addAll(chips);
        } else{
            selectedChips.clear();
        }
    }

    public ArrayList<CharSequence> getSelectedChips() {return selectedChips;}

    public void setIndex(int i) {index = i;}

    public int getIndex(){return index;}
}
