package com.example.lanto.bakingtime.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Recipe;

import java.util.List;

public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.MainAdapterViewHolder>{

    private List<Recipe> mRecipes;

    @NonNull
    @Override
    public MainAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycle_item, parent, false);

        return new MainAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapterViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        holder.recipeNameTextView.setText(recipe.getName());
        Log.e("Adapter", "" + recipe.getName());
    }

    @Override
    public int getItemCount() {
        if(mRecipes == null) return 0;
        return mRecipes.size();
    }

    public class MainAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView recipeNameTextView;

        MainAdapterViewHolder(View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.main_item_text_view);
        }
    }

    public void setRecipes(List<Recipe> recipes){
        mRecipes = recipes;
    }


}
