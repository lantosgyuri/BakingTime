package com.example.lanto.bakingtime.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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
import android.widget.Toast;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.data.Recipe;
import com.example.lanto.bakingtime.data.Step;
import com.example.lanto.bakingtime.githubservice.Network;
import com.example.lanto.bakingtime.ui.adapter.MainRecycleAdapter;
import com.example.lanto.bakingtime.viewmodel.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;
public class MainFragment extends Fragment implements MainRecycleAdapter.OnItemClickListener{

    public static final String BUN_INGREDIENT = "Ingredients";
    public static final String BUN_STEP = "Steps";
    public static final String BUN_RECIPENAME = "RecipeName";

    private mainFragmentClickListener mClickListener;
    private MainRecycleAdapter mainRecycleAdapter;

    public MainFragment (
            //empty constructor
    ){}

    //interface to pass data to host activity
    public interface mainFragmentClickListener{
        void mainFragOnClick(Bundle bundle);
    }

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

        //set up item click listener
        mainRecycleAdapter.setOnItemClickListener(this);
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

    //send data to the host Activity
    @Override
    public void OnItemClick(int position) {
        Recipe currentRecipe = mainRecycleAdapter.getItem(position);
        // get recipe name
        String recipeName = currentRecipe.getName();
        //first make ArrayList from List
        List<Ingredient> ingredientsList = currentRecipe.getIngredients();
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>(ingredientsList.size());
        ingredientArrayList.addAll(ingredientsList);

        List<Step> stepList = currentRecipe.getSteps();
        ArrayList<Step> stepArrayList = new ArrayList<>(stepList.size());
        stepArrayList.addAll(stepList);

        //then put in Bundle
        Bundle bundle = new Bundle();
        bundle.putString(BUN_RECIPENAME, recipeName);
        bundle.putParcelableArrayList(BUN_INGREDIENT, ingredientArrayList);
        bundle.putParcelableArrayList(BUN_STEP, stepArrayList);
        mClickListener.mainFragOnClick(bundle);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mClickListener = (mainFragmentClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement interface");
        }
    }
}
