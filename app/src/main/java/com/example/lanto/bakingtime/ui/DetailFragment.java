package com.example.lanto.bakingtime.ui;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.data.Step;
import com.example.lanto.bakingtime.ui.adapter.DetailStepsAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

    private static final String ARG_INGREDIENTS = "argIngredients";
    private static final String ARG_STEPS = "argSteps";

    private ArrayList<Ingredient> ingredientsList;
    private ArrayList<Step> stepList;

    private Button ingredientsButton;

    private DetailOnClickListener clickListener;

    //interface to send data to host Activity
    public interface DetailOnClickListener{
        void ingredientOnClick(ArrayList<Ingredient> ingredients);
        void stepOnClick(Step step);
    }

    //make new Fragment with the new Data
    public static DetailFragment newInstance(ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_INGREDIENTS, ingredients);
        args.putParcelableArrayList(ARG_STEPS, steps);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ingredientsButton = rootView.findViewById(R.id.detail_ingredients_button);

        if (getArguments() != null) {
            ingredientsList = getArguments().getParcelableArrayList(ARG_INGREDIENTS);
            stepList = getArguments().getParcelableArrayList(ARG_STEPS);
        }

        //set up RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.detail_fragment_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DetailStepsAdapter detailStepsAdapter = new DetailStepsAdapter();
        detailStepsAdapter.setStepList(stepList);
        recyclerView.setAdapter(detailStepsAdapter);

        //send the ingredients Array List to host Activity
        ingredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.ingredientOnClick(ingredientsList);
            }
        });

        //set RecyclerView Adapter Click Listener
        detailStepsAdapter.setItemClickListener(new DetailStepsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Step currentStep = stepList.get(position);
                //send Step Object to Host Activity
                clickListener.stepOnClick(currentStep);
            }
        });

        return rootView;
    }

    // link click listener interface to host Activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            clickListener = (DetailOnClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement interface");
        }
    }
}
