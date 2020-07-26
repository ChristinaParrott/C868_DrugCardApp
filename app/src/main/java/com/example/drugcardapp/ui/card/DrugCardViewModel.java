package com.example.drugcardapp.ui.card;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.drugcardapp.database.AppRepository;
import com.example.drugcardapp.database.DrugCardEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DrugCardViewModel extends AndroidViewModel {
    public MutableLiveData<DrugCardEntity> mcard = new MutableLiveData<>();

    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public DrugCardViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }
    public void loadData(int cardId) {
        executor.execute(() -> {
            DrugCardEntity card = mRepository.getCardById(cardId);
            mcard.postValue(card);
        });
    }

    public void saveCard(String genericStr, String tradeStr, String classStr, String systemStr,
                         String actionStr, String sideEffectStr, String adverseStr, String interactionsStr,
                         String implicationsStr, String otherStr) {
        DrugCardEntity card = mcard.getValue();

        if(card == null){
            if (TextUtils.isEmpty(genericStr) || TextUtils.isEmpty(classStr) || TextUtils.isEmpty(systemStr))
                return;
            else {
                card = new DrugCardEntity(genericStr, tradeStr, classStr, systemStr, actionStr, sideEffectStr,
                        adverseStr,interactionsStr,implicationsStr,otherStr);
            }
        } else {
            card.setGenericName(genericStr);
            card.setTradeName(tradeStr);
            card.setDrugClass(classStr);
            card.setDrugSystem(systemStr);
            card.setActionMechanism(actionStr);
            card.setSideEffects(sideEffectStr);
            card.setAdverseReactions(adverseStr);
            card.setInteractions(interactionsStr);
            card.setImplications(implicationsStr);
            card.setOther(otherStr);
        }
        mRepository.insertCard(card);
    }

    public void deleteCard() {
        mRepository.deleteCard(mcard.getValue());
    }
}
