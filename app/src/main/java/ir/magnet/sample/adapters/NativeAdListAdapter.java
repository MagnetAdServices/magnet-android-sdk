package ir.magnet.sample.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ir.magnet.sample.R;
import ir.magnet.sdk.MagnetNativeContentAd;
import ir.magnet.sdk.ViewBinder;

/**
 * Created by Hesami on 01/03/2016.
 */
public class NativeAdListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> mTitles;
    private ArrayList<String> mDescriptions;
    private Activity mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public NativeAdListAdapter(ArrayList<String> titles, ArrayList<String> descriptions, Activity context) {
        mDescriptions = descriptions;
        mTitles = titles;
        mContext = context;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class AdFrameViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public FrameLayout adLayout;
        public AdFrameViewHolder(View v) {
            super(v);
            adLayout = (FrameLayout) v.findViewById(R.id.nativeAdFrame);
        }
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView headlineTextView;
        public TextView descriptionTextView;
        public ImageView advertiserIcon;
        public ImageView advertiseImage;
        public Button callToActionButton;

        public ListItemViewHolder(View v) {
            super(v);
            headlineTextView = (TextView) v.findViewById(R.id.native_title);
            descriptionTextView = (TextView) v.findViewById(R.id.native_text);
            advertiserIcon = (ImageView) v.findViewById(R.id.native_icon_image);
            advertiseImage = (ImageView) v.findViewById(R.id.native_main_image);
            callToActionButton = (Button) v.findViewById(R.id.native_cta);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 5 != 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                // create a new view
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.native_content, parent, false);
                // set the view's size, margins, paddings and layout parameters
                ListItemViewHolder listHolder = new ListItemViewHolder(v1);
                return listHolder;
            case 2:
                // create a new view
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.advertise_frame, parent, false);
                // set the view's size, margins, paddings and layout parameters
                AdFrameViewHolder adHolder = new AdFrameViewHolder(v2);
                return adHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof AdFrameViewHolder) {
            ViewBinder viewBinder = new ViewBinder.Builder()
                    .titleId(R.id.native_title)
                    .descriptionId(R.id.native_text)
                    .iconImageId(R.id.native_icon_image)
                    .imageId(R.id.native_main_image)
                    .callToActionId(R.id.native_cta)
                    .build();
            RelativeLayout adView = (RelativeLayout) mContext.getLayoutInflater().inflate(R.layout.native_content, null);
            MagnetNativeContentAd magnetNativeContentad = MagnetNativeContentAd.create(mContext);
            magnetNativeContentad.buildNativeAdView(adView, viewBinder);
            magnetNativeContentad.load("141cec5b4eec418d9c3ee4030a8f3ea1", ((AdFrameViewHolder) holder).adLayout);

        } else {
            final String title = mTitles.get(position);
            final String description = mDescriptions.get(position);
            ((ListItemViewHolder) holder).advertiserIcon.setImageResource(R.drawable.ic_launcher);
            ((ListItemViewHolder) holder).headlineTextView.setText(title);
            ((ListItemViewHolder) holder).descriptionTextView.setText(description);
            ((ListItemViewHolder) holder).advertiseImage.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitles.size();
    }
}
