package com.example.lanto.bakingtime.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.data.Step;

import java.util.ArrayList;

public class StepOrIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_or_ingredient);

        Intent inComingIntent = getIntent();

        //set the title
        setTitle(inComingIntent.getStringExtra(MainFragment.BUN_RECIPENAME));

        int flag = inComingIntent.getIntExtra(DetailActivity.INT_FLAG, 0);

        if(flag == DetailActivity.STEP){
            Step currentStep = inComingIntent.getParcelableExtra(DetailActivity.INTENT_STEP);

            StepFragment stepFragment = StepFragment.newInstance(currentStep);
            getSupportFragmentManager().beginTransaction().replace(R.id.step_or_ingredient_container, stepFragment).commit();

        //make an Ingredient Fragment and show it
        } else if (flag == DetailActivity.INGREDIENT){
            ArrayList<Ingredient> ingredients = inComingIntent.getParcelableArrayListExtra(DetailActivity.INTENT_INGREDIENTS);

            IngredientFragment ingredientFragment = IngredientFragment.newInstance(ingredients);
            getSupportFragmentManager().beginTransaction().replace(R.id.step_or_ingredient_container, ingredientFragment).commit();
        }

    }

}
