package com.example.drugcardapp.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;


@Entity(tableName = "quizzes")
public class QuizEntity {
    @PrimaryKey(autoGenerate = true)
    private int quizID;
    private String quizName;
    private ArrayList<DrugCardEntity> quizCards;


    @Ignore
    public QuizEntity() { }

    public QuizEntity(int quizID, String quizName, ArrayList<DrugCardEntity> quizCards) {
        this.quizID = quizID;
        this.quizName = quizName;
        this.quizCards = quizCards;
    }
    @Ignore
    public QuizEntity(String quizName, ArrayList<DrugCardEntity> quizCards) {
        this.quizName = quizName;
        this.quizCards = quizCards;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public ArrayList<DrugCardEntity> getQuizCards() {
        return quizCards;
    }

    public void setQuizCards(ArrayList<DrugCardEntity> quizCards) {
        this.quizCards = quizCards;
    }
    @Override
    public String toString() {return quizName;}
}