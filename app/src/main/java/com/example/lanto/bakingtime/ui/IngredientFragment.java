package com.example.lanto.bakingtime.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.ui.adapter.IngredientAdapter;

import java.util.ArrayList;

public class IngredientFragment extends Fragment {
    private static final String ARG_INGREDIENTS = "argIngredients";
    private ArrayList<Ingredient> ingredientList;

    //make new Fragment with the new Data
    public static IngredientFragment newInstance(ArrayList<Ingredient> ingredients) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_INGREDIENTS, ingredients);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);


        if (getArguments() != null) {
            ingredientList = getArguments().getParcelableArrayList(ARG_INGREDIENTS);
        }
        RecyclerView recyclerView = rootView.findViewById(R.id.ingredient_fragment_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        IngredientAdapter ingredientAdapter = new IngredientAdapter();
        ingredientAdapter.setIngredientList(ingredientList);
        recyclerView.setAdapter(ingredientAdapter);


        return rootView;
    }
}
