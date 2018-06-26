package com.example.lanto.bakingtime.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private ArrayList<Ingredient> mIngredientList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_recycle_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient currentIngredient = mIngredientList.get(position);

        holder.nameTextView.setText(currentIngredient.getIngredient());
        holder.quantityTextView.setText("" + currentIngredient.getQuantity());
        holder.measureTextView.setText(currentIngredient.getMeasure());
    }

    @Override
    public int getItemCount() {
        if (mIngredientList == null) return 0;
        return mIngredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTextView;
        private TextView quantityTextView;
        private TextView measureTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.ingredient_name);
            quantityTextView = itemView.findViewById(R.id.ingredient_quantity);
            measureTextView = itemView.findViewById(R.id.ingredient_measure);
        }
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList){
        mIngredientList = ingredientList;
    }
}
