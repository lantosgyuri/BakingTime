package com.example.lanto.bakingtime;

import android.util.Log;

import com.example.lanto.bakingtime.data.Recipe;
import com.example.lanto.bakingtime.githubservice.RecipeClient;
import com.example.lanto.bakingtime.githubservice.ServiceGenerator;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static final String LOG_TAG = Repository.class.getSimpleName();

    //get the recipes from web with retrofit
    public static List<Recipe> getRecipesFromServer(){
        final List<Recipe> recipes = Collections.EMPTY_LIST;
        RecipeClient client = ServiceGenerator.createService(RecipeClient.class);
        Call<List<Recipe>> call = client.getRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipeList = response.body();
                Log.e("retrofit,"," " + recipeList.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(LOG_TAG, "Retrofit onFailure" + t.getMessage());
            }
        });

        return recipes;
    }


}
