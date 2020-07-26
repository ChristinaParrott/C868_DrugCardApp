package com.example.drugcardapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {DrugCardEntity.class, QuizEntity.class}, version = 5)
@TypeConverters({DateConverter.class, ArrayListConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "DrugCardDB.db";
    public static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract DrugCardDAO drugCardDAO();
    public abstract QuizDAO quizDAO();

    public static AppDatabase getInstance(Context context){
        if (instance == null){
            synchronized (LOCK) {
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}