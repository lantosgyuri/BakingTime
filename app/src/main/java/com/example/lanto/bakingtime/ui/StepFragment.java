package com.example.lanto.bakingtime.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.data.Step;

import java.util.ArrayList;

public class StepFragment extends Fragment {

    private static final String ARG_STEP = "argStep";
    private Step mStep;

    //make new Fragment with the new Data
    public static StepFragment newInstance(Step step) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_STEP, step);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

        TextView description = rootView.findViewById(R.id.step_description);

        if(getArguments() != null){
            mStep = getArguments().getParcelable(ARG_STEP);
            description.setText(mStep.getDescription());
        }

        return rootView;
    }
}
