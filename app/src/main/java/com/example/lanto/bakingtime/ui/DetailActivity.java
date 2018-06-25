package com.example.lanto.bakingtime.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.data.Step;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

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
}
