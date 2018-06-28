package com.example.lanto.bakingtime.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Step;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

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
    private boolean autoPlay;

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
        autoPlay = true;

        initializePlayer(mUrl);

        return rootView;
    }

    private void initializePlayer(String url){
        mPlayerView.requestFocus();

        mBandwidthMeter = new DefaultBandwidthMeter();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(mBandwidthMeter);
        mTrackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), mTrackSelector);

        mExoPlayer.setPlayWhenReady(autoPlay);

        mMediaDataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), "BakingTime"), mBandwidthMeter);

        MediaSource mediaSource = new ExtractorMediaSource.Factory(mMediaDataSourceFactory)
                .createMediaSource(Uri.parse(url));

        mExoPlayer.prepare(mediaSource);
        mPlayerView.setPlayer(mExoPlayer);

    }
}
