package com.example.drugcardapp.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "drugCards")
public class DrugCardEntity {
    @PrimaryKey(autoGenerate = true)
    private int cardID;
    private String genericName;
    private String tradeName;
    private String drugClass;
    private String drugSystem;
    private String actionMechanism;
    private String sideEffects;
    private String adverseReactions;
    private String interactions;
    private String implications;
    private String other;
    private boolean isSelected;

    @Ignore
    public DrugCardEntity(){    }

    public DrugCardEntity(int cardID, String genericName, String tradeName, String drugClass, String drugSystem,
                          String actionMechanism, String sideEffects, String adverseReactions,
                          String interactions, String implications, String other) {
        this.cardID = cardID;
        this.genericName = genericName;
        this.tradeName = tradeName;
        this.drugClass = drugClass;
        this.drugSystem = drugSystem;
        this.actionMechanism = actionMechanism;
        this.sideEffects = sideEffects;
        this.adverseReactions = adverseReactions;
        this.interactions = interactions;
        this.implications = implications;
        this.other = other;
        this.isSelected = false;
    }
    @Ignore
    public DrugCardEntity(String genericName, String tradeName, String drugClass, String drugSystem, String actionMechanism,
                          String sideEffects, String adverseReactions, String interactions,
                          String implications, String other) {
        this.genericName = genericName;
        this.tradeName = tradeName;
        this.drugClass = drugClass;
        this.drugSystem = drugSystem;
        this.actionMechanism = actionMechanism;
        this.sideEffects = sideEffects;
        this.adverseReactions = adverseReactions;
        this.interactions = interactions;
        this.implications = implications;
        this.other = other;
        this.isSelected = false;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getDrugClass() {
        return drugClass;
    }

    public void setDrugClass(String drugClass) {
        this.drugClass = drugClass;
    }

    public String getDrugSystem() {
        return drugSystem;
    }

    public void setDrugSystem(String drugSystem) {
        this.drugSystem = drugSystem;
    }

    public String getActionMechanism() {
        return actionMechanism;
    }

    public void setActionMechanism(String actionMechanism) {
        this.actionMechanism = actionMechanism;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getAdverseReactions() {
        return adverseReactions;
    }

    public void setAdverseReactions(String adverseReactions) {
        this.adverseReactions = adverseReactions;
    }

    public String getInteractions() {
        return interactions;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

    public String getImplications() {
        return implications;
    }

    public void setImplications(String implications) {
        this.implications = implications;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
    @Override
    public String toString() {return genericName;}

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
