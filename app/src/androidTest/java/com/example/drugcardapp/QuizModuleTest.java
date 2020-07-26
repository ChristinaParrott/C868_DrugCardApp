package com.example.drugcardapp;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.drugcardapp.database.AppDatabase;
import com.example.drugcardapp.database.DrugCardDAO;
import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.database.QuizDAO;
import com.example.drugcardapp.database.QuizEntity;
import com.example.drugcardapp.util.SampleCards;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class QuizModuleTest {
    public static final String TAG = "Junit";
    private AppDatabase mDB;
    private DrugCardDAO drugCardDAO;
    private QuizDAO quizDAO;

    @Before
    public void createDB(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDB = Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class).build();
        drugCardDAO = mDB.drugCardDAO();
        quizDAO = mDB.quizDAO();
        Log.i(TAG,"created DB");
    }
    @After
    public void closeDB(){
        mDB.close();
        Log.i(TAG,"closed DB");
    }
    @Test
    public void createAndRetrieveQuizzes(){
        drugCardDAO.insertAllDrugCards(SampleCards.getCards());
        quizDAO.insertAllQuizzes(SampleCards.getQuizzes());
        int count = quizDAO.getQuizCount();

        assertEquals(SampleCards.getQuizzes().size(), count);
        Log.i(TAG, "Create and Retrieve Quizzes Passed");
    }
    @Test
    public void printAndCompareQuizzes() {
        drugCardDAO.insertAllDrugCards(SampleCards.getCards());
        quizDAO.insertAllQuizzes(SampleCards.getQuizzes());
        for (int i = 1; i <= quizDAO.getQuizCount(); i++) {
            assertEquals(quizDAO.getQuizByID(i).getQuizName(),
                    SampleCards.getQuizzes().get(i-1).getQuizName());
        }
        Log.i(TAG, "Print and Compare Quizzes Passed");
    }
    @Test
    public void newQuiz(){
        final String DRUG_NAME_6 = "metformin";
        final String DRUG_TRADE_6 = "Glucophage";
        final String DRUG_CLASS_6 = "Biguanides";
        final String DRUG_SYSTEM_6 = "Endocrine";
        final String DRUG_ACTION_6 = "Decreases hepatic glucose production. Decreases intestinal glucose absorption. Increases insulin sensitivity.";
        final String DRUG_SE_6 = "abd bloating, dirrahea, N&V, hypoglycemia";
        final String DRUG_ADVERSE_6 = "lactic acidosis";
        final String DRUG_INT_6 = "ETOH, iodinated contrast media";
        final String DRUG_IMP_6 = "Hold if contrast media must be used due to risk for lactic acidosis. Monitor for renal dysfunction";
        final String DRUG_OTHER_6 = "Instruct pt to monitor blood glucose for hyper/hypogylcemia";
        ArrayList<DrugCardEntity> Endocards = new ArrayList<>();
        Endocards.add(new DrugCardEntity(DRUG_NAME_6, DRUG_TRADE_6,DRUG_CLASS_6,DRUG_SYSTEM_6, DRUG_ACTION_6,
                DRUG_SE_6,DRUG_ADVERSE_6,DRUG_INT_6,DRUG_IMP_6, DRUG_OTHER_6));
        QuizEntity test = new QuizEntity("Test Quiz", Endocards);
        quizDAO.insertQuiz(test);
        assertEquals(quizDAO.getQuizByID(1).getQuizName(), "Test Quiz");
        Log.i(TAG, "Add Quiz Passed");
    }
    @Test
    public void editQuiz(){
        drugCardDAO.insertAllDrugCards(SampleCards.getCards());
        quizDAO.insertAllQuizzes(SampleCards.getQuizzes());
        final String DRUG_NAME_6 = "metformin";
        final String DRUG_TRADE_6 = "Glucophage";
        final String DRUG_CLASS_6 = "Biguanides";
        final String DRUG_SYSTEM_6 = "Endocrine";
        final String DRUG_ACTION_6 = "Decreases hepatic glucose production. Decreases intestinal glucose absorption. Increases insulin sensitivity.";
        final String DRUG_SE_6 = "abd bloating, dirrahea, N&V, hypoglycemia";
        final String DRUG_ADVERSE_6 = "lactic acidosis";
        final String DRUG_INT_6 = "ETOH, iodinated contrast media";
        final String DRUG_IMP_6 = "Hold if contrast media must be used due to risk for lactic acidosis. Monitor for renal dysfunction";
        final String DRUG_OTHER_6 = "Instruct pt to monitor blood glucose for hyper/hypogylcemia";
        ArrayList<DrugCardEntity> Endocards = new ArrayList<>();
        Endocards.add(new DrugCardEntity(DRUG_NAME_6, DRUG_TRADE_6,DRUG_CLASS_6,DRUG_SYSTEM_6, DRUG_ACTION_6,
                DRUG_SE_6,DRUG_ADVERSE_6,DRUG_INT_6,DRUG_IMP_6, DRUG_OTHER_6));
        QuizEntity test = new QuizEntity(1,"Test Edit Quiz", Endocards);
        quizDAO.insertQuiz(test);
        assertEquals(quizDAO.getQuizByID(1).getQuizName(), "Test Edit Quiz");
        Log.i(TAG, "Edit Quiz Passed");
    }
    @Test
    public void deleteQuiz(){
        drugCardDAO.insertAllDrugCards(SampleCards.getCards());
        quizDAO.insertAllQuizzes(SampleCards.getQuizzes());
        final String quizName = quizDAO.getQuizByID(1).getQuizName();
        quizDAO.deleteQuiz(quizDAO.getQuizByID(1));
        assertEquals(quizDAO.getQuizByName(quizName), null);
        Log.i(TAG, "Delete Quiz Passed");
    }
}
