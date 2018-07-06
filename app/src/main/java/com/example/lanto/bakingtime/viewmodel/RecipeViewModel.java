package com.example.lanto.bakingtime.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.lanto.bakingtime.data.Recipe;
import com.example.lanto.bakingtime.database.RecipeDatabase;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private LiveData<List<Recipe>> mRecipes;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        final RecipeDatabase db = RecipeDatabase.getsInstance(this.getApplication());
        mRecipes = db.recipeDao().loadAllRecipe();
    }

    public LiveData<List<Recipe>> getRecipes(){
            return mRecipes;
    }
}
