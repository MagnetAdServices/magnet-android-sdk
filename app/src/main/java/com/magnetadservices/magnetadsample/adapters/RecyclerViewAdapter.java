package com.magnetadservices.magnetadsample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.magnetadservices.magnetadsample.viewHolders.CountryViewHolder;
import com.magnetadservices.magnetadsample.R;
import com.magnetadservices.magnetadsample.models.Country;
import com.magnetadservices.sdk.MagnetNativeContentAd;
import com.magnetadservices.sdk.MagnetNativeViewBinder;

import java.util.List;

/**
 * Created by Esmaeili on 2017-08-19.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Object> data;

    public RecyclerViewAdapter(Context context, List<Object> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_native, parent, false);
                return new CountryViewHolder(itemView);
            case 1:
                View adView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view, parent, false);
                return new AdViewHolder(adView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            MagnetNativeViewBinder viewBinder = null;
            try {
                viewBinder = new MagnetNativeViewBinder.Builder()
                        .iconImageId(R.id.icon)
                        .adIndicativeId(R.id.ad_indicative)
                        .titleId(R.id.title)
                        .descriptionId(R.id.description)
                        .callToActionId(R.id.call_to_action)
                        .imageId(R.id.image)
                        .build();
            } catch (Exception e) {
                Log.e("Magnet Native", e.getMessage());
            }

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout contentView = (RelativeLayout) inflater.inflate(R.layout.item_native, null);
            MagnetNativeContentAd ad = (MagnetNativeContentAd) data.get(position);
            ad.buildNativeAdView(contentView, viewBinder);
            ad.load("AdUnitId", ((AdViewHolder)holder).rootView);
        }
        else {
            Country country = (Country) data.get(position);
            CountryViewHolder countryHolder = (CountryViewHolder) holder;

            countryHolder.titleView.setText(country.getName());
            countryHolder.descriptionView.setText(country.getDescription());
            countryHolder.iconView.setImageResource(country.getLogo());
            countryHolder.imageView.setImageResource(country.getImage());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof Country) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    private class AdViewHolder extends RecyclerView.ViewHolder {

        FrameLayout rootView;

        AdViewHolder(View itemView) {
            super(itemView);
            rootView = (FrameLayout) itemView.findViewById(R.id.frame_native);
        }
    }
}
