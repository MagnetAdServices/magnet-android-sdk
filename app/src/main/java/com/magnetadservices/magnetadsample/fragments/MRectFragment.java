package com.magnetadservices.magnetadsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.magnetadservices.magnetadsample.R;
import com.magnetadservices.sdk.MagnetAdLoadListener;
import com.magnetadservices.sdk.MagnetMRectAd;
import com.magnetadservices.sdk.MagnetAdSize;

/**
 * Created by Mohammad on 14/12/2016.
 */

public class MRectFragment extends Fragment {
    FrameLayout frMRectBanner;
    Button btnShowMRect;
    Button btnStopMRect;
    MagnetMRectAd magnetMRectAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);
        frMRectBanner = view.findViewById(R.id.frMRectBanner);
        btnShowMRect = view.findViewById(R.id.btnShowMRect);
        btnStopMRect = view.findViewById(R.id.btnStopMRect);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        magnetMRectAd = MagnetMRectAd.create(getContext());
        btnShowMRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMRectBanner();
            }
        });
        btnStopMRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                magnetMRectAd.stop();
            }
        });

    }

    private void showMRectBanner() {
        magnetMRectAd.setAdLoadListener(new MagnetAdLoadListener() {
            @Override
            public void onPreload(int i, String s) {

            }

            @Override
            public void onReceive() {
                Toast.makeText(getContext(), "MRect load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(int i, String s) {
                Toast.makeText(getContext(), "MRect load failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose() {

            }
        });
        magnetMRectAd.load("AdUnitId",frMRectBanner, MagnetAdSize.SIZE_MEDIUM_RECTANGLE);
    }
}