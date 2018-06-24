package com.example.lanto.bakingtime.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Recipe;
import com.example.lanto.bakingtime.githubservice.Network;
import com.example.lanto.bakingtime.ui.adapter.MainRecycleAdapter;
import com.example.lanto.bakingtime.viewmodel.RecipeViewModel;

import java.util.List;
public class MainFragment extends Fragment {


    public MainFragment (
            //empty constructor
    ){}

    private MainRecycleAdapter mainRecycleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //set up RecycleView
        final RecyclerView recyclerView = rootView.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainRecycleAdapter = new MainRecycleAdapter();
        recyclerView.setAdapter(mainRecycleAdapter);

        //check network connection
        if(isNetworkAvailable(getActivity())){
            //refresh database
            Network.getRecipesAndSaveInDB(getActivity());
        } else {
            Toast.makeText(getActivity(), R.string.Internet_warning_toast, Toast.LENGTH_LONG).show();
        }

        //set up View model
        retrieveRecipes();

        return rootView;
    }

    //check network available or not
    private boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void retrieveRecipes(){
        RecipeViewModel viewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        viewModel.getRecipes().observe(getActivity(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mainRecycleAdapter.setRecipes(recipes);
                mainRecycleAdapter.notifyDataSetChanged();
            }
        });
    }

}
