package com.example.lanto.bakingtime.ui.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Step;

import java.util.List;

public class DetailStepsAdapter extends RecyclerView.Adapter<DetailStepsAdapter.StepsViewHolder> {
    private List<Step> mStepList;
    private int selectedPos = RecyclerView.NO_POSITION;

    private onItemClickListener mItemClickListener;

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_step_recycle_item, parent, false);
        return new StepsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        Step currentStep = mStepList.get(position);
        holder.itemView.setBackgroundColor(selectedPos == position ? Color.LTGRAY: Color.TRANSPARENT);
        holder.titleTextView.setText(currentStep.getShortDescription());
        holder.imageView.setImageResource(R.drawable.img_211437);

    }

    @Override
    public int getItemCount() {
        return mStepList == null ? 0 : mStepList.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder{
        final TextView titleTextView;
        final ImageView imageView;

        StepsViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.detail_step_image);
            titleTextView = itemView.findViewById(R.id.detail_step_title_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //set color to selected item
                    notifyItemChanged(selectedPos);
                    selectedPos = getLayoutPosition();
                    notifyItemChanged(selectedPos);
                    //
                    if (mItemClickListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public void setStepList(List<Step> stepList){
        mStepList = stepList;
    }

    public void setItemClickListener(onItemClickListener listener){
        mItemClickListener = listener;
    }


}
