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
import com.magnetadservices.sdk.MagnetMRectSize;

/**
 * Created by Mohammad on 14/12/2016.
 */

public class Tab2Fragment extends Fragment {
    FrameLayout frMRectBanner;
    Button btnShowMRect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);

        frMRectBanner = (FrameLayout) view.findViewById(R.id.frMRectBanner);
        btnShowMRect = (Button) view.findViewById(R.id.btnShowMRect);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnShowMRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMRectBanner();
            }
        });

    }

    private void showMRectBanner() {
        MagnetMRectAd magnetMRectAd = MagnetMRectAd.create(getContext());
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
        });
        magnetMRectAd.load("AdUnitId",frMRectBanner, MagnetMRectSize.SIZE_300_250);
    }
}