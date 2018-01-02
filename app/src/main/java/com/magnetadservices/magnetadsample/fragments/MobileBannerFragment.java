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

public class MobileBannerFragment extends Fragment {
    FrameLayout frBanner;
    Button btnShowBanner;
    Button btnStopBanner;
    MagnetMobileBannerAd bannerAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        btnShowBanner = view.findViewById(R.id.btnShowBanner);
        btnStopBanner = view.findViewById(R.id.btnStopBanner);
        frBanner = view.findViewById(R.id.frBanner);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bannerAd = MagnetMobileBannerAd.create(getContext());
        btnShowBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBanner();
            }
        });
        btnStopBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerAd.stop();
            }
        });
    }

    private void showBanner() {
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
