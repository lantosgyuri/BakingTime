package com.example.lanto.bakingtime.ui;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.data.Recipe;
import com.example.lanto.bakingtime.database.RecipeDatabase;

import java.util.List;

import static com.example.lanto.bakingtime.widget.WidgeIntentService.INT_VALUE_KEY;
import static com.example.lanto.bakingtime.widget.WidgeIntentService.PREF_NAME;

public class MainActivity extends AppCompatActivity implements MainFragment.mainFragmentClickListener {

    public static final String BUN_BUNDLE = "Bundle";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //get the data from fragment and send to Detail Activity
    @Override
    public void mainFragOnClick(Bundle bundle) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(BUN_BUNDLE, bundle);
        startActivity(intent);
    }

}
