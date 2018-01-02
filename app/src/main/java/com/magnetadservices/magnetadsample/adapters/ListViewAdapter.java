package com.magnetadservices.magnetadsample.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.magnetadservices.magnetadsample.viewHolders.CountryViewHolder;
import com.magnetadservices.magnetadsample.R;
import com.magnetadservices.magnetadsample.models.Country;
import com.magnetadservices.sdk.MagnetNativeAdvanced;
import com.magnetadservices.sdk.MagnetNativeViewBinder;

import java.util.List;

/**
 * Created by Esmaeili on 2017-08-20.
 */

public class ListViewAdapter extends ArrayAdapter<Object> {

    private List<Object> data;

    public ListViewAdapter(@NonNull Context context, @NonNull List<Object> data) {
        super(context, R.layout.item_view, data);
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof Country) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        int type = getItemViewType(position);

        switch (type) {
            case 0:
                CountryViewHolder holder;

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_native, parent, false);
                    holder = new CountryViewHolder(convertView);
                    convertView.setTag(holder);
                }
                else {
                    holder = (CountryViewHolder) convertView.getTag();
                }

                Country country = (Country) data.get(position);

                holder.titleView.setText(country.getName());
                holder.descriptionView.setText(country.getDescription());
                holder.iconView.setImageResource(country.getLogo());
                holder.imageView.setVisibility(View.GONE);

                return convertView;

            default:

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_view, parent, false);
                }
                MagnetNativeAdvanced ad = (MagnetNativeAdvanced) data.get(position);
                MagnetNativeViewBinder viewBinder = null;
                try {
                    viewBinder = new MagnetNativeViewBinder.Builder()
                            .iconImageId(R.id.icon)
                            .adIndicativeId(R.id.ad_indicative)
                            .titleId(R.id.title)
                            .descriptionId(R.id.description)
                            .callToActionId(R.id.call_to_action)
                            .build();
                } catch (Exception e) {
                    Log.e("Magnet Native", e.getMessage());
                }
                RelativeLayout contentView = (RelativeLayout) inflater.inflate(R.layout.item_native, null);
                contentView.findViewById(R.id.image).setVisibility(View.GONE);
                ad.buildNativeAdView(contentView, viewBinder);
                ad.load("AdUnitId", (ViewGroup) convertView);

                return convertView;
        }
    }
}