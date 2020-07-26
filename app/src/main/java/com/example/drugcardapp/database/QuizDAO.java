package com.example.drugcardapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuizDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuiz(QuizEntity quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllQuizzes(List<QuizEntity> quizzes);

    @Delete
    void deleteQuiz(QuizEntity quiz);

    @Query("SELECT * FROM quizzes WHERE quizID = :id")
    QuizEntity getQuizByID(int id);

    @Query("SELECT * FROM quizzes ORDER BY quizName DESC")
    LiveData<List<QuizEntity>> getAllQuizzes();

    @Query("DELETE FROM quizzes")
    int deleteAllQuizzes();

    @Query("SELECT COUNT(*) FROM quizzes")
    int getQuizCount();

    @Query("SELECT * FROM quizzes WHERE quizName LIKE :quizName")
    QuizEntity getQuizByName(String quizName);
}
