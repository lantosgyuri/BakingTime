package com.example.lanto.bakingtime.data;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class IngredientTypeConverter {

    private static final Gson gson = new Gson();

    //converting the Lists to String to save in SQL
    @TypeConverter
    public static String ingredientListToString (List<Ingredient> list){
        return gson.toJson(list);
    }

    //converting Json text to objects
    @TypeConverter
    public static List<Ingredient> jsonToIngredintList(String json){
        if (json == null) return Collections.emptyList();
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        return gson.fromJson(json, type);

    }
}
