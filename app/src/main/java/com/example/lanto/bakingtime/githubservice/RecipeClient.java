package com.example.lanto.bakingtime.githubservice;

import com.example.lanto.bakingtime.data.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RecipeClient {

    @GET("59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();
}
