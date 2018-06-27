package com.example.lanto.bakingtime.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.data.Step;

import java.util.ArrayList;
import java.util.List;

public class StepOrIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_or_ingredient);

        Intent inComingIntent = getIntent();

        int flag = inComingIntent.getIntExtra("FLAG", 0);
        Log.e("Harmadik Activity", "" + flag);

        if(flag == 2){
            Step currentStep = inComingIntent.getParcelableExtra("Step");
            Log.e("Harmadik Activity", "" + currentStep.getDescription());

            StepFragment stepFragment = StepFragment.newInstance(currentStep);
            getSupportFragmentManager().beginTransaction().replace(R.id.step_or_ingredient_container, stepFragment).commit();

        //make an Ingredient Fragment and show it
        } else if (flag == 1){
            ArrayList<Ingredient> ingredients = inComingIntent.getParcelableArrayListExtra("Ingredients");
            Log.e("harmadik activity", "" + ingredients.get(0).getIngredient());

            IngredientFragment ingredientFragment = IngredientFragment.newInstance(ingredients);
            getSupportFragmentManager().beginTransaction().replace(R.id.step_or_ingredient_container, ingredientFragment).commit();
        }

    }

}
