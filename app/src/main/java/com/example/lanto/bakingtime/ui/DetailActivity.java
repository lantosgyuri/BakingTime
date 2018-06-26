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

public class DetailActivity extends AppCompatActivity implements DetailFragment.DetailOnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get Bundle
        Bundle bundle = this.getIntent().getBundleExtra("Bundle");
        if(bundle != null) {
            //get Ingredient and Step ArrayLists from bundle
            ArrayList<Ingredient> ingredientArrayList = bundle.getParcelableArrayList("Ingredients");
            ArrayList<Step> stepArrayList = bundle.getParcelableArrayList("Steps");

            // make a new Detail fragment with the new data
            DetailFragment detailFragment = DetailFragment.newInstance(ingredientArrayList, stepArrayList);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, detailFragment).commit();
        }
    }

    @Override
    public void ingredientOnClick(ArrayList<Ingredient> ingredients) {
        Intent intent = new Intent(this, StepOrIngredientActivity.class);
        intent.putParcelableArrayListExtra("Ingredients", ingredients);
        intent.putExtra("FLAG", 1);
        startActivity(intent);
    }

    @Override
    public void stepOnClick(Step step) {
        Intent intent = new Intent(this, StepOrIngredientActivity.class);
        intent.putExtra("Step", step);
        intent.putExtra("FLAG", 2);
        startActivity(intent);
    }
}
