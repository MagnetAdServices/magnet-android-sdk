package com.magnetadservices.magnetadsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.magnetadservices.magnetadsample.R;
import com.magnetadservices.sdk.MagnetAdLoadListener;
import com.magnetadservices.sdk.MagnetNativeAdvanced;
import com.magnetadservices.sdk.MagnetNativeViewBinder;

/**
 * Created by Mohammad on 14/12/2016.
 */

public class NativeFragment extends Fragment {
    FrameLayout frNative;
    Button btnNative;
    RelativeLayout nativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment, container, false);
        nativeLayout = (RelativeLayout) View.inflate(getContext(), R.layout.native_content, null);
        frNative = view.findViewById(R.id.frNative);
        btnNative = view.findViewById(R.id.btnNative);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnNative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNativeAd();
            }
        });
    }

    private void showNativeAd() {
        if (nativeLayout.getParent() != null) {
            ((ViewGroup) nativeLayout.getParent()).removeView(nativeLayout);
        }
        MagnetNativeViewBinder viewBinder = null;
        try {
            viewBinder = new MagnetNativeViewBinder.Builder()
                    .iconImageId(R.id.imageview)
                    .adIndicativeId(R.id.ad_indicative)
                    .titleId(R.id.title_native)
                    .descriptionId(R.id.text_native)
                    .callToActionId(R.id.button6)
                    .imageId(R.id.imageView2)
                    .build();
        } catch (Exception e) {
            Log.e("Magnet Native", e.getMessage());
        }

        MagnetNativeAdvanced magnetNativeAdvanced = MagnetNativeAdvanced.create(getContext());
        magnetNativeAdvanced.setAdLoadListener(new MagnetAdLoadListener() {
            @Override
            public void onPreload(int i, String s) {

            }

            @Override
            public void onReceive() {
                Toast.makeText(getContext(), "native load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(int i, String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose() {

            }
        });
        magnetNativeAdvanced.buildNativeAdView(nativeLayout, viewBinder);
        magnetNativeAdvanced.load("AdUnitId", frNative);
    }
}
