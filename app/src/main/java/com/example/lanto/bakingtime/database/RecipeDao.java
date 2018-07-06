package com.example.lanto.bakingtime.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.lanto.bakingtime.data.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    //I need for this project just a query and an insert method
    @Query("SELECT * FROM recipes")
    LiveData<List<Recipe>> loadAllRecipe();

    @Query("SELECT name FROM recipes")
    List<String> loadAllRecipeName();

    @Query("SELECT * FROM recipes WHERE id = :idTag")
    Recipe loadChoosedIngredient(int idTag);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(Recipe... recipes);
}
