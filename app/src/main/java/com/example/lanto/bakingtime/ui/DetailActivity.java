package com.example.lanto.bakingtime.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.data.Step;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements DetailFragment.DetailOnClickListener{

    public static final String INTENT_INGREDIENTS = "Ingredients";
    public static final String INTENT_STEP = "Step";
    public static final String INT_FLAG = "FLAG";

    public static final int INGREDIENT = 1;
    public static final int STEP = 2;
    private boolean mTwoPane;
    private String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //check which layout is loaded (tablet or not)
        if(findViewById(R.id.tablet_detail_container) != null) mTwoPane = true;

        // get Bundle
        Bundle bundle = this.getIntent().getBundleExtra(MainActivity.BUN_BUNDLE);
        if(bundle != null) {
            //set the title with the recipe name
            recipeName = bundle.getString(MainFragment.BUN_RECIPENAME);
            setTitle(recipeName);
            //get Ingredient and Step ArrayLists from bundle
            ArrayList<Ingredient> ingredientArrayList = bundle.getParcelableArrayList(MainFragment.BUN_INGREDIENT);
            ArrayList<Step> stepArrayList = bundle.getParcelableArrayList(MainFragment.BUN_STEP);

            // make a new Detail fragment with the new data
            DetailFragment detailFragment = DetailFragment.newInstance(ingredientArrayList, stepArrayList);
            if (mTwoPane){
                getSupportFragmentManager().beginTransaction().replace(R.id.tablet_steps_container, detailFragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, detailFragment).commit();
            }
        }
    }

    @Override
    public void ingredientOnClick(ArrayList<Ingredient> ingredients) {
        if(!mTwoPane) {
            Intent intent = new Intent(this, StepOrIngredientActivity.class);
            intent.putParcelableArrayListExtra(INTENT_INGREDIENTS, ingredients);
            intent.putExtra(INT_FLAG, INGREDIENT);
            intent.putExtra(MainFragment.BUN_RECIPENAME, recipeName);
            startActivity(intent);
        } else {
            IngredientFragment ingredientFragment = IngredientFragment.newInstance(ingredients);
            getSupportFragmentManager().beginTransaction().replace(R.id.tablet_detail_container, ingredientFragment).commit();
        }
    }

    @Override
    public void stepOnClick(Step step) {
        if(!mTwoPane) {
            Intent intent = new Intent(this, StepOrIngredientActivity.class);
            intent.putExtra(INTENT_STEP, step);
            intent.putExtra(INT_FLAG, STEP);
            intent.putExtra(MainFragment.BUN_RECIPENAME, recipeName);
            startActivity(intent);
        } else {
            StepFragment stepFragment = StepFragment.newInstance(step);
            getSupportFragmentManager().beginTransaction().replace(R.id.tablet_detail_container, stepFragment).commit();
        }
    }
}
