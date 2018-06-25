package com.example.lanto.bakingtime.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Step;

import java.util.List;

public class DetailStepsAdapter extends RecyclerView.Adapter<DetailStepsAdapter.StepsViewHolder> {
    private List<Step> mStepList;

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
        holder.titleTextView.setText(currentStep.getShortDescription());

    }

    @Override
    public int getItemCount() {
        if(mStepList == null) return 0;
        return mStepList.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;

        public StepsViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.detail_step_title_text);
        }
    }

    public void setStepList(List<Step> stepList){
        mStepList = stepList;
    }
}