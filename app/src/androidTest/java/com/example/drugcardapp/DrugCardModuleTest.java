package com.example.drugcardapp;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.drugcardapp.database.AppDatabase;
import com.example.drugcardapp.database.DrugCardDAO;
import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.util.SampleCards;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DrugCardModuleTest {
    public static final String TAG = "Junit";
    private AppDatabase mDB;
    private DrugCardDAO drugCardDAO;
    @Before
    public void createDB(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDB = Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class).build();
        drugCardDAO = mDB.drugCardDAO();
        Log.i(TAG,"created DB");
    }
    @After
    public void closeDB(){
        mDB.close();
        Log.i(TAG,"closed DB");
    }
    @Test
    public void createAndRetrieveCards(){
        drugCardDAO.insertAllDrugCards(SampleCards.getCards());
        int count = drugCardDAO.getDrugCardCount();
        assertEquals(SampleCards.getCards().size(), count);
        Log.i(TAG, "Created and Retrieve Cards Passed");
    }
    @Test
    public void printAndCompareCards() {
        drugCardDAO.insertAllDrugCards(SampleCards.getCards());
        for (int i = 1; i <= drugCardDAO.getDrugCardCount(); i++) {
            assertEquals(drugCardDAO.getCardByID(i).getGenericName(),
                    SampleCards.getCards().get(i-1).getGenericName());
        }
        Log.i(TAG, "Print and Compare Cards Passed");
    }
    @Test
    public void newCard(){
            final String DRUG_NAME_2 = "new";
            final String DRUG_TRADE_2 = "Zestril";
            final String DRUG_CLASS_2 = "Angiotensin-Converting Enzyme Inhibitor";
            final String DRUG_SYSTEM_2 = "Cardiovascular";
            final String DRUG_ACTION_2 = "Blocks conversion of angiotensin I to angiotensin II";
            final String DRUG_SE_2 = "fatigue, weakness, bradycardia";
            final String DRUG_ADVERSE_2 = "Angioedema";
            final String DRUG_INT_2 = "NSAIDs, antihypertensives, diuretics, potassium supplements";
            final String DRUG_IMP_2 = "Persistent dry cough";
            final String DRUG_OTHER_2 = "n/a";
            DrugCardEntity newCard = new DrugCardEntity(DRUG_NAME_2, DRUG_TRADE_2,DRUG_CLASS_2,DRUG_SYSTEM_2, DRUG_ACTION_2,
                    DRUG_SE_2,DRUG_ADVERSE_2,DRUG_INT_2,DRUG_IMP_2, DRUG_OTHER_2);
            drugCardDAO.insertDrugCard(newCard);
            assertEquals(drugCardDAO.getCardByName(DRUG_NAME_2).getGenericName(), DRUG_NAME_2);
        Log.i(TAG, "New Card Passed");
    }
    @Test
    public void editCard(){
        final String DRUG_NAME_2 = "edit";
        final String DRUG_TRADE_2 = "Zestril";
        final String DRUG_CLASS_2 = "Angiotensin-Converting Enzyme Inhibitor";
        final String DRUG_SYSTEM_2 = "Cardiovascular";
        final String DRUG_ACTION_2 = "Blocks conversion of angiotensin I to angiotensin II";
        final String DRUG_SE_2 = "fatigue, weakness, bradycardia";
        final String DRUG_ADVERSE_2 = "Angioedema";
        final String DRUG_INT_2 = "NSAIDs, antihypertensives, diuretics, potassium supplements";
        final String DRUG_IMP_2 = "Persistent dry cough";
       final String DRUG_OTHER_2 = "n/a";
       DrugCardEntity editedCard = new DrugCardEntity(1, DRUG_NAME_2, DRUG_TRADE_2,DRUG_CLASS_2,DRUG_SYSTEM_2, DRUG_ACTION_2,
               DRUG_SE_2,DRUG_ADVERSE_2,DRUG_INT_2,DRUG_IMP_2, DRUG_OTHER_2);
        drugCardDAO.insertDrugCard(editedCard);
        assertEquals(drugCardDAO.getCardByID(1).getGenericName(), DRUG_NAME_2);
        Log.i(TAG, "Edit Card Passed");
    }
    @Test
    public void deleteCard(){
        drugCardDAO.insertAllDrugCards(SampleCards.getCards());
        final String cardName = drugCardDAO.getCardByID(1).getGenericName();
        drugCardDAO.deleteDrugCard(drugCardDAO.getCardByID(1));
        assertEquals(drugCardDAO.getCardByName(cardName), null);
        Log.i(TAG, "Delete Card Passed");
    }
}
