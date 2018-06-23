package com.example.lanto.bakingtime.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.Repository;
import com.example.lanto.bakingtime.data.Recipe;
import com.example.lanto.bakingtime.ui.adapter.MainRecycleAdapter;

import java.util.List;

public class MainFragment extends Fragment {

    //empty constructor
    public MainFragment (){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MainRecycleAdapter mainRecycleAdapter = new MainRecycleAdapter();
        mainRecycleAdapter.setRecipes(Repository.getRecipesFromServer());

        recyclerView.setAdapter(mainRecycleAdapter);
        Log.e("MainFragment", "adapter set ready");


        return rootView;
    }
}
