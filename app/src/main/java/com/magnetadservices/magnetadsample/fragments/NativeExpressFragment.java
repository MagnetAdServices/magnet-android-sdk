package com.magnetadservices.magnetadsample.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.magnetadservices.magnetadsample.R;
import com.magnetadservices.sdk.AdSize;
import com.magnetadservices.sdk.MagnetAdLoadListener;
import com.magnetadservices.sdk.MagnetNativeExpress;

/**
 * Created by Esmaeili on 2017-11-18.
 */

public class NativeExpressFragment extends Fragment {

    FrameLayout frNative;
    Button btnNative;
    RelativeLayout nativeLayout;
    private int width;

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

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
    }

    private void showNativeAd() {
        if (nativeLayout.getParent() != null) {
            ((ViewGroup) nativeLayout.getParent()).removeView(nativeLayout);
        }

        MagnetNativeExpress magnetNativeExpress = MagnetNativeExpress.create(getContext());
        magnetNativeExpress.setAdLoadListener(new MagnetAdLoadListener() {
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
        magnetNativeExpress.load("AdUnitId", frNative, new AdSize(width, 180));
    }
}
