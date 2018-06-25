package com.example.lanto.bakingtime.githubservice;

import android.content.Context;
import android.util.Log;

import com.example.lanto.bakingtime.data.Recipe;
import com.example.lanto.bakingtime.database.RecipeDatabase;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Network {

    private static final String LOG_TAG = Network.class.getSimpleName();

    public static void getRecipesAndSaveInDB(final Context context){
        RecipeClient client = ServiceGenerator.createService(RecipeClient.class);
        Call<List<Recipe>> call = client.getRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipeList = response.body();
                Log.e("retrofit,"," " + recipeList.get(0).getName());
                saveInDb(context, recipeList);

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(LOG_TAG, "Retrofit onFailure" + t.getMessage());
            }
        });
    }

    public static void saveInDb(Context context, final List<Recipe> recipes){
        final RecipeDatabase db = RecipeDatabase.getsInstance(context);

        //BG thread to insert
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //make array from list to bulk insert
                Recipe[] recipeArray = new Recipe[recipes.size()];
                recipeArray = recipes.toArray(recipeArray);
                db.recipeDao().bulkInsert(recipeArray);
            }
        });
    }
}