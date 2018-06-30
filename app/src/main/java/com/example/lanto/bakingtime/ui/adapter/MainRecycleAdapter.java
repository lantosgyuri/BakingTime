package com.example.lanto.bakingtime.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Recipe;

import java.util.List;

public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.MainAdapterViewHolder>{

    private List<Recipe> mRecipes;
    private OnItemClickListener mListener;
    private Context mCOntext;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public MainRecycleAdapter(Context context){
        mCOntext = context;
    }

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
        //set image from the res/array
        TypedArray images = mCOntext.getResources().obtainTypedArray(R.array.dessert_icons);
        holder.recipeImage.setImageResource(images.getResourceId(position, 0));
    }

    @Override
    public int getItemCount() {
        if(mRecipes == null) return 0;
        return mRecipes.size();
    }

    public class MainAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView recipeNameTextView;
        ImageView recipeImage;

        MainAdapterViewHolder(final View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.main_item_text_view);
            recipeImage = itemView.findViewById(R.id.main_item_image_view);

            //on item click listener set up
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public void setRecipes(List<Recipe> recipes){
        mRecipes = recipes;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mListener = onItemClickListener;
    }

    public Recipe getItem(int position){
        return mRecipes.get(position);
    }


}
