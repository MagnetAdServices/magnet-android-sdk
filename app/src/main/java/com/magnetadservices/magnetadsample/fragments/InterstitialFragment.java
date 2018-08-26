package com.magnetadservices.magnetadsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.magnetadservices.magnetadsample.R;
import com.magnetadservices.sdk.MagnetAdLoadListener;
import com.magnetadservices.sdk.MagnetInterstitialAd;

/**
 * Created by Saeed on 1/11/17.
 */

public class InterstitialFragment extends Fragment {
    Button btnLoadInterstitial;
    Button btnShowInterstitial;
    MagnetInterstitialAd interstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab4_fragment, container, false);
        btnLoadInterstitial = view.findViewById(R.id.btnInterstitial);
        btnShowInterstitial = view.findViewById(R.id.btnShowInterstitial);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnLoadInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadInterstitial();
            }
        });
        btnShowInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(interstitialAd);
                if (interstitialAd != null) {
                    System.out.println(interstitialAd.isAdReady());
                }
                if (interstitialAd != null && interstitialAd.isAdReady()) {
                    interstitialAd.show();
                }
            }
        });
    }

    private void loadInterstitial() {
        interstitialAd = MagnetInterstitialAd.create(getContext());
        interstitialAd.setAdLoadListener(new MagnetAdLoadListener() {
            @Override
            public void onPreload(int i, String s) {

            }

            @Override
            public void onReceive() {
                Toast.makeText(getContext(), "interstitial receive", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(int i, String s) {
                Toast.makeText(getContext(), "interstitial load failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose() {

            }
        });
        interstitialAd.load("AdUnitId");
    }
}
