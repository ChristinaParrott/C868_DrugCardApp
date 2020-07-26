package com.example.drugcardapp.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ArrayListConverter {
    @TypeConverter
    public static ArrayList<DrugCardEntity> fromString(String value) {
        Type listType = new TypeToken<ArrayList<DrugCardEntity>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<DrugCardEntity> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
