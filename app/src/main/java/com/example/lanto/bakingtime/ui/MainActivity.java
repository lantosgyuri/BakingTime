package com.example.lanto.bakingtime.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lanto.bakingtime.R;

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
