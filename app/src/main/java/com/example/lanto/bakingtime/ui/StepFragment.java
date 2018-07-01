package com.example.lanto.bakingtime.ui;


import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Step;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static android.view.View.GONE;

public class StepFragment extends Fragment {

    private static final String ARG_STEP = "argStep";

    private Step mStep;
    private String mDescription;
    private String mUrl;
    private String mThumbnailUrl;

    private TextView mDescriptionTextView;
    private PlayerView mPlayerView;

    private SimpleExoPlayer mExoPlayer;
    private DefaultBandwidthMeter mBandwidthMeter;
    private DataSource.Factory mMediaDataSourceFactory;
    private DefaultTrackSelector mTrackSelector;

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

        mDescriptionTextView = rootView.findViewById(R.id.step_description);
        mPlayerView = rootView.findViewById(R.id.player_view);

        if(getArguments() != null){
            mStep = getArguments().getParcelable(ARG_STEP);
            mDescription = mStep.getDescription();
            mUrl = mStep.getVideoURL();
            mThumbnailUrl = mStep.getThumbnailURL();
        }

        mDescriptionTextView.setText(mDescription);

        //if there is no video to play than we don't need the playerview
        if(mUrl.length() == 0){
            mPlayerView.setVisibility(GONE);
        } else   initializePlayer(mUrl);

        if(mExoPlayer != null) {
            mPlayerView.setPlayer(mExoPlayer);
        }

        checkScreenMode();

        return rootView;
    }

    //initialize ExoPlayer
    private void initializePlayer(String url){
        mPlayerView.requestFocus();

        mBandwidthMeter = new DefaultBandwidthMeter();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(mBandwidthMeter);
        mTrackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), mTrackSelector);

        mExoPlayer.setPlayWhenReady(true);

        mMediaDataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), "BakingTime"), mBandwidthMeter);

        MediaSource mediaSource = new ExtractorMediaSource.Factory(mMediaDataSourceFactory)
                .createMediaSource(Uri.parse(url));

        mExoPlayer.prepare(mediaSource);
        //set playerView to always Fit in the screen
        mPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
        mPlayerView.setPlayer(mExoPlayer);

    }

    //release player
    private void releasePlayer(){
        mExoPlayer.release();
        mExoPlayer = null;
        mTrackSelector = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //release player just when user navigates away
        if(!getActivity().isChangingConfigurations() && mExoPlayer != null){
            releasePlayer();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        checkScreenMode();
    }

    private void checkScreenMode() {
        if (mUrl.length() != 0) {
            //checking is there a Landscape or Portrait mode
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //hide the Textview and the ActionBar
                mDescriptionTextView.setVisibility(GONE);
                ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                //show actionBar and Textview
                mDescriptionTextView.setVisibility(View.VISIBLE);
                ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            }
        }
    }
}
