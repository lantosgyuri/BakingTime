package com.example.lanto.bakingtime.data;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class StepTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Step> stringToStepList(String json) {
        if (json == null) return Collections.emptyList();
        Type listType = new TypeToken<List<Step>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    @TypeConverter
    public static String stepListToJson(List<Step> someObjects) {
        return gson.toJson(someObjects);
    }
}
