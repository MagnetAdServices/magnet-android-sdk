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
import com.magnetadservices.sdk.MagnetRewardAd;
import com.magnetadservices.sdk.MagnetRewardListener;

/**
 * Created by Esmaeili on 2018-01-07.
 */

public class RewardedFragment extends Fragment {

    Button btnLoadRewarded;
    Button btnShowRewarded;
    MagnetRewardAd rewardAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab4_fragment, container, false);
        btnLoadRewarded = view.findViewById(R.id.btnInterstitial);
        btnShowRewarded = view.findViewById(R.id.btnShowInterstitial);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnLoadRewarded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadRewarded();
            }
        });
        btnShowRewarded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(rewardAd);
                if (rewardAd != null) {
                    System.out.println(rewardAd.isAdReady());
                }
                if (rewardAd != null && rewardAd.isAdReady()) {
                    rewardAd.show(new MagnetRewardListener() {
                        @Override
                        public void onFinish(boolean isRewardSuccessful) {
                            Toast.makeText(getContext(), "reward successful: " + isRewardSuccessful, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void loadRewarded() {
        rewardAd = MagnetRewardAd.create(getContext());
        rewardAd.setAdLoadListener(new MagnetAdLoadListener() {
            @Override
            public void onPreload(int i, String s) {

            }

            @Override
            public void onReceive() {
                Toast.makeText(getContext(), "reward receive", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(int i, String s) {
                Toast.makeText(getContext(), "reward load failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose() {

            }
        });
        rewardAd.load("AdUnitId");
    }
}
