package com.example.drugcardapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DrugCardDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDrugCard(DrugCardEntity drugCard);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllDrugCards(List<DrugCardEntity> drugCards);

    @Delete
    void deleteDrugCard(DrugCardEntity drugCard);

    @Query("SELECT * FROM drugCards WHERE cardID = :id")
    DrugCardEntity getCardByID(int id);

    @Query("SELECT * FROM drugCards ORDER BY genericName DESC")
    LiveData<List<DrugCardEntity>> getAllDrugCards();

    @Query("DELETE FROM drugCards")
    int deleteAllDrugCards();

    @Query("SELECT COUNT(*) FROM drugCards")
    int getDrugCardCount();

    @Query("SELECT * FROM drugCards WHERE drugSystem LIKE :systemStr")
    LiveData<List<DrugCardEntity>> getCardsBySystem(String systemStr);

    @Query("SELECT * FROM drugCards WHERE genericName LIKE :drugName")
    DrugCardEntity getCardByName(String drugName);
}
