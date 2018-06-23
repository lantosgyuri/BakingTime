package com.example.lanto.bakingtime.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.lanto.bakingtime.data.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes")
    List<Recipe> loadAllRecipe();
}
