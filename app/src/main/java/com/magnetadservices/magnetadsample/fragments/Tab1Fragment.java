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
import com.magnetadservices.sdk.MagnetMobileBannerAd;

/**
 * Created by Mohammad on 14/12/2016.
 */

public class Tab1Fragment extends Fragment {
    FrameLayout frBanner;
    Button btnShowBanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        btnShowBanner = (Button) view.findViewById(R.id.btnShowBanner);
        frBanner = (FrameLayout) view.findViewById(R.id.frBanner);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnShowBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBanner();
            }
        });
    }

    private void showBanner() {
        MagnetMobileBannerAd bannerAd = MagnetMobileBannerAd.create(getContext());
        bannerAd.setAdLoadListener(new MagnetAdLoadListener() {
            @Override
            public void onPreload(int i, String s) {

            }

            @Override
            public void onReceive() {
                Toast.makeText(getContext(), "Banner load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(int i, String s) {
                Toast.makeText(getContext(), "Banner load failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose() {

            }
        });
        bannerAd.load("AdUnitId", frBanner);
    }
}
