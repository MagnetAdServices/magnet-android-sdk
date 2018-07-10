package com.magnetadservices.magnetadsample.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
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
    private MagnetNativeExpress magnetNativeExpress;
    private DisplayMetrics metrics;
    private int width;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment, container, false);
        frNative = view.findViewById(R.id.frNative);
        btnNative = view.findViewById(R.id.btnNative);
        metrics = getResources().getDisplayMetrics();

        magnetNativeExpress = MagnetNativeExpress.create(getContext());
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
        ViewTreeObserver vto = frNative.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    frNative.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    frNative.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                width = (int) (frNative.getMeasuredWidth() / metrics.density);
            }
        });

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
        // TODO: replace the adUnitId with yours
        magnetNativeExpress.load("2f84c1f29e0408d5b305f98aad0e008e", frNative, new AdSize(width, width));
    }
}
