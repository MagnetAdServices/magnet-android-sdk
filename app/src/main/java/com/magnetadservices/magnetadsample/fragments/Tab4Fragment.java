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
import com.magnetadservices.sdk.MagnetInterstitialAd;
import com.magnetadservices.sdk.MagnetMobileBannerAd;

import static java.security.AccessController.getContext;

/**
 * Created by Saeed on 1/11/17.
 */

public class Tab4Fragment extends Fragment {
    FrameLayout frBanner;
    Button btnShowInterstitial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab4_fragment, container, false);

        btnShowInterstitial = (Button) view.findViewById(R.id.btnInterstitial);
        frBanner = (FrameLayout) view.findViewById(R.id.frBanner);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnShowInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBanner();
            }
        });

    }

    private void showBanner() {
        final MagnetInterstitialAd interstitialAd = MagnetInterstitialAd.create(getContext());
        interstitialAd.load("AdUnitId");

        interstitialAd.setAdLoadListener(new MagnetAdLoadListener() {
            @Override
            public void onPreload(int i, String s) {

            }

            @Override
            public void onReceive() {
                if(interstitialAd.isAdReady()){
                    interstitialAd.show();
                }
            }

            @Override
            public void onFail(int i, String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
