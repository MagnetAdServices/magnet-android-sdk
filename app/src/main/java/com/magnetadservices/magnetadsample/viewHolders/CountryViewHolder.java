package com.magnetadservices.magnetadsample.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.magnetadservices.magnetadsample.R;

/**
 * Created by Esmaeili on 2017-08-20.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView descriptionView;
    public ImageView iconView;
    public ImageView imageView;

    public CountryViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.title);
        descriptionView = (TextView) itemView.findViewById(R.id.description);
        iconView = (ImageView) itemView.findViewById(R.id.icon);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        itemView.findViewById(R.id.call_to_action).setVisibility(View.GONE);
        itemView.findViewById(R.id.ad_indicative).setVisibility(View.GONE);
    }
}