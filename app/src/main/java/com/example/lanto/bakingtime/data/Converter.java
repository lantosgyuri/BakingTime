package com.example.lanto.bakingtime.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    private static Gson gson = new Gson();

    //converting the Lists to String to save in SQL
    public static String convertIngredientList(List<Ingredient> list){
        return gson.toJson(list);
    }

    public static String convertStepList(List<Step> list){
        return gson.toJson(list);
    }

    //converting Json text to objects
    public static List<Ingredient> convertIgredientJson(String json){
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        return gson.fromJson(json, type);

    }

    public static List<Step> convertStepJson(String json){
        Type type = new TypeToken<List<Step>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
